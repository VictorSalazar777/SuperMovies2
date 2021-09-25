package com.manuelsoft.supermovies2.repository

import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMovie

interface Repository {

    suspend fun getGenres(): List<Genre>
    suspend fun popularMoviesByGenre(genreId: String): List<PopularMovie>

    fun saveSelectedPopularMovie(popularMovie: PopularMovie)
    fun loadSelectedPopularMovie(): PopularMovie

}