package com.daniel.watchsgs.data.repository

import com.daniel.watchsgs.data.api.WatchModeAPI
import com.daniel.watchsgs.data.model.TitleDetails
import com.daniel.watchsgs.data.model.TitlesResponse
import com.daniel.watchsgs.util.Constants
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WatchModeRepository @Inject constructor(
    val watchModeAPI: WatchModeAPI
){
    fun getMoviesAndTvSeries(): Single<Pair<TitlesResponse, TitlesResponse>> {
        val apiKey = Constants.WATCHMODE_API_KEY
        return Single.zip(
            watchModeAPI.getMovies(apiKey),
            watchModeAPI.getTvSeries(apiKey)
        )
        { moviesResponse, tvSeriesResponse ->
            Pair(moviesResponse, tvSeriesResponse)
        }
    }

    fun getTitleDetails(titleId: Int): Single<TitleDetails>  {
        val apiKey = Constants.WATCHMODE_API_KEY
        return watchModeAPI.getTitleDetails(titleId = titleId, apiKey = apiKey)
    }
}