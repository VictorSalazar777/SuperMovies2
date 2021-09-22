package com.manuelsoft.supermovies2.repository

import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMoviesByGenre

interface Repository {

    suspend fun getGenres() : List<Genre>
    suspend fun popularMoviesByGenre(genre : String) : List<PopularMoviesByGenre>

    fun saveGenre(genre: Genre)
    fun loadGenre() : Genre

}