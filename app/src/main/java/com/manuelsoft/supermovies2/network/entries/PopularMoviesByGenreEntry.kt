package com.manuelsoft.supermovies2.network.entries

import com.google.gson.annotations.SerializedName
import com.manuelsoft.supermovies2.model.PopularMoviesByGenre

data class PopularMoviesByGenreEntry(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularMoviesByGenre>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
