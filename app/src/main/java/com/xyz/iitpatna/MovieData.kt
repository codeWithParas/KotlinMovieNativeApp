package com.xyz.iitpatna



data class MovieData(
    val page: Int,
    val results: List<Movie>
)

data class Movie(
    val title: String,
    val poster_path: String
)