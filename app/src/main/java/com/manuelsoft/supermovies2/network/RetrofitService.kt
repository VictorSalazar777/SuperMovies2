package com.manuelsoft.supermovies2.network

import com.manuelsoft.supermovies2.model.DiscoverMoviesResult
import com.manuelsoft.supermovies2.model.GenresResponse

interface RetrofitService {
    suspend fun getGenres() : GenresResponse

    suspend fun getDiscoveredMovies() : DiscoverMoviesResult

    suspend fun getDiscoveredMovies(genre: String) : DiscoverMoviesResult
}