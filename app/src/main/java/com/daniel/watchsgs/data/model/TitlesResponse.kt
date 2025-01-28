package com.daniel.watchsgs.data.model

data class TitlesResponse(
    val titles: List<Title>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)

data class Title(
    val id: Int,
    val title: String,
    val year: Int,
    val imdb_id: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val type: String
)

