package com.manuelsoft.supermovies2.network

import com.manuelsoft.supermovies2.model.DiscoverMoviesResult
import com.manuelsoft.supermovies2.network.entries.GenresEntry
import com.manuelsoft.supermovies2.network.entries.PopularMoviesByGenreEntry

interface RetrofitService {
    suspend fun getGenres() : GenresEntry

    suspend fun getDiscoveredMovies() : DiscoverMoviesResult

    suspend fun getDiscoveredMovies(genre: String) : DiscoverMoviesResult

    suspend fun popularMoviesByGenre(genre : String) : PopularMoviesByGenreEntry
}