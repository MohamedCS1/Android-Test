package com.example.domain.models.trendingMovies


data class TrendingMoviesResponse(
    val page: Int? = null,
    val results: List<Movie>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
):java.io.Serializable