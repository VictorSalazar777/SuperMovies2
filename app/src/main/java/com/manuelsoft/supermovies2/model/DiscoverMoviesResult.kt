package com.manuelsoft.supermovies2.model

import com.google.gson.annotations.SerializedName


data class DiscoverMoviesResult(
    @field:SerializedName("page")
    val page : Int,
    @field:SerializedName("results")
    val movieListResult : List<MovieResult>,
    @field:SerializedName("total_results")
    val totalResults : Int,
    @field:SerializedName("total_pages")
    val totalPages : Int
)

data class MovieResult(
    @field:SerializedName("poster_path")
    val poster_path: String?,
    @field:SerializedName("adult")
    val adult: Boolean,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("release_date")
    val release_date: String,
    @field:SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("original_title")
    val original_title: String,
    @field:SerializedName("original_language")
    val original_language: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("backdrop_path")
    val backdrop_path: String?,
    @field:SerializedName("popularity")
    val popularity: Float,
    @field:SerializedName("vote_count")
    val vote_count: Int,
    @field:SerializedName("video")
    val video: Boolean,
    @field:SerializedName("vote_average")
    val vote_average: Float
)