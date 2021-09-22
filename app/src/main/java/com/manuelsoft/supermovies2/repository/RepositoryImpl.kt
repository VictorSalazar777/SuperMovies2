package com.manuelsoft.supermovies2.repository

import android.content.Context
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMoviesByGenre
import com.manuelsoft.supermovies2.network.RetrofitServiceImpl

class RepositoryImpl(context: Context) : Repository {

    private val retrofitService = RetrofitServiceImpl(context)
    private lateinit var genre: Genre

    override suspend fun getGenres(): List<Genre> {
        return retrofitService.getGenres().genreList
    }

    override suspend fun popularMoviesByGenre(genreId: String): List<PopularMoviesByGenre> {
        return retrofitService.popularMoviesByGenre(genreId).results
    }

    override fun saveGenre(genre: Genre) {
        this.genre = genre
    }

    override fun loadGenre(): Genre {
        return genre
    }
}