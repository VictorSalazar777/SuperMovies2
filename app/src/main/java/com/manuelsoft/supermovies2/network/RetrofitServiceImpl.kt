package com.manuelsoft.supermovies2.network

import com.manuelsoft.supermovies2.model.DiscoverMoviesResult
import com.manuelsoft.supermovies2.network.entries.GenresEntry
import com.manuelsoft.supermovies2.network.entries.PopularMoviesByGenreEntry
import javax.inject.Inject


class RetrofitServiceImpl
@Inject
constructor(private val moviesDbApi: MoviesDbApi,
            private val apiKey: String) : RetrofitService {

    override suspend fun getGenres(): GenresEntry {
        return moviesDbApi.getGenres(apiKey)
    }

    override suspend fun getDiscoveredMovies(): DiscoverMoviesResult {
        return moviesDbApi.discoverMovies(apiKey)
    }

    override suspend fun getDiscoveredMovies(genre: String): DiscoverMoviesResult {
        return moviesDbApi.discoverMovies(apiKey, genre)
    }

    override suspend fun popularMoviesByGenre(genreId: String): PopularMoviesByGenreEntry {

        // Include family genre in all queries
        val familyId = "10751"
        val genreIds = "$genreId,$familyId"

        return moviesDbApi.popularMoviesByGenre(
            apiKey, "popularity.desc",
            "false", genreIds
        )
    }
}