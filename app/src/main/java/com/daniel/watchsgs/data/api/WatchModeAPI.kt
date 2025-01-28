package com.daniel.watchsgs.data.api

import com.daniel.watchsgs.data.model.TitleDetails
import com.daniel.watchsgs.data.model.TitlesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchModeAPI {

    @GET("list-titles/")
    fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String = "movie"
    ) : Single<TitlesResponse>

    @GET("list-titles/")
    fun getTvSeries(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String = "tv_series"
    ) : Single<TitlesResponse>

    @GET("title/{title_id}/details/")
    fun getTitleDetails(
        @Path("title_id") titleId: Int,
        @Query("apiKey") apiKey: String
    ) : Single<TitleDetails>
}