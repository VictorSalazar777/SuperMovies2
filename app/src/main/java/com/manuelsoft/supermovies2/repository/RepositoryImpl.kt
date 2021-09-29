package com.manuelsoft.supermovies2.repository

import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMovie
import com.manuelsoft.supermovies2.network.RetrofitService
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) : Repository {

    private lateinit var selectedPopularMovie: PopularMovie

    override suspend fun getGenres(): List<Genre> {
        return retrofitService.getGenres().genreList
    }

    override suspend fun popularMoviesByGenre(genreId: String): List<PopularMovie> {
        return retrofitService.popularMoviesByGenre(genreId).results
    }

    override fun saveSelectedPopularMovie(popularMovie: PopularMovie) {
        this.selectedPopularMovie = popularMovie
    }

    override fun loadSelectedPopularMovie(): PopularMovie {
        return selectedPopularMovie
    }
}