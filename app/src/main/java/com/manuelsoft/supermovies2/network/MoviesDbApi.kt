package com.manuelsoft.supermovies2.network

import com.manuelsoft.supermovies2.model.DiscoverMoviesResult
import com.manuelsoft.supermovies2.network.entries.GenresEntry
import com.manuelsoft.supermovies2.network.entries.PopularMoviesByGenreEntry
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDbApi {

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): GenresEntry

    @GET("discover/movie?include_adult=false&include_video=false&page=1&language=en-US&sort_by=popularity.desc")
    suspend fun discoverMovies(@Query("api_key") apiKey: String): DiscoverMoviesResult

    @GET("discover/movie?include_adult=false&include_video=false&page=1&language=en-US&sort_by=popularity.desc")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genre: String
    ): DiscoverMoviesResult

    @GET("discover/movie")
    suspend fun popularMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: String,
        @Query("with_genres") genreId: String
    ): PopularMoviesByGenreEntry
}