package com.daniel.watchsgs.ui.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.daniel.watchsgs.data.model.Title
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
class MainViewModel @Inject constructor(
    private val repository: WatchModeRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Title>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _tvShows = MutableStateFlow<List<Title>>(emptyList())
    val tvShows = _tvShows.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    @SuppressLint("CheckResult")
    fun getMoviesAndTvSeries() {
        _isLoading.value = true
        repository.getMoviesAndTvSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _isLoading.value = false }
            .subscribe(
                { (moviesResponse, tvSeriesResponse) ->
                    _movies.value = moviesResponse.titles
                    _tvShows.value = tvSeriesResponse.titles
                },
                { error ->
                    Logger.loge("getMoviesAndTvSeries ${error.message ?: "Unknown Error"}")
                    _errorMessage.value = error.message ?: "Unknown Error"
                    _isLoading.value = false
                }
            )
    }
}
