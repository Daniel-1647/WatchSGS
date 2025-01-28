package com.daniel.watchsgs.ui.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.daniel.watchsgs.data.model.TitleDetails
import com.daniel.watchsgs.data.repository.WatchModeRepository
import com.daniel.watchsgs.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: WatchModeRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _titleDetails = MutableStateFlow<TitleDetails?>(null)
    val titleDetails: StateFlow<TitleDetails?> = _titleDetails.asStateFlow()

    @SuppressLint("CheckResult")
    fun getTitleDetails(titleId: Int) {
        _isLoading.value = true
        repository.getTitleDetails(titleId = titleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _isLoading.value = false }
            .subscribe(
                { details ->
                    _titleDetails.value = details
                },
                { error ->
                    Logger.loge("getTitleDetailsError ${error.message ?: "Unknown Error"}")
                    _errorMessage.value = error.message ?: "Unknown Error"
                    _isLoading.value = false
                }
            )
    }
}