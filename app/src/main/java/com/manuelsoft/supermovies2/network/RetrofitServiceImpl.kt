package com.manuelsoft.supermovies2.network

import android.content.Context
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.model.DiscoverMoviesResult
import com.manuelsoft.supermovies2.network.entries.GenresEntry
import com.manuelsoft.supermovies2.network.entries.PopularMoviesByGenreEntry

class RetrofitServiceImpl(context: Context) : RetrofitService {

    private val retrofitApi =
        RetrofitProvider.getRetrofitService(context.getString(R.string.base_url))
    private val apiKey = context.getString(R.string.themoviedb_api_key)

    override suspend fun getGenres(): GenresEntry {
        return retrofitApi.getGenres(apiKey)
    }

    override suspend fun getDiscoveredMovies(): DiscoverMoviesResult {
        return retrofitApi.discoverMovies(apiKey)
    }

    override suspend fun getDiscoveredMovies(genre: String): DiscoverMoviesResult {
        return retrofitApi.discoverMovies(apiKey, genre)
    }

    override suspend fun popularMoviesByGenre(genreId: String): PopularMoviesByGenreEntry {
        return retrofitApi.popularMoviesByGenre(
            apiKey, "popularity.desc",
            "false", genreId
        )
    }


}