package com.manuelsoft.supermovies2.network

import com.manuelsoft.supermovies2.model.GenresResponse
import com.manuelsoft.supermovies2.model.DiscoverMoviesResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): GenresResponse

    @GET("discover/movie?include_adult=false&include_video=false&page=1&language=en-US&sort_by=popularity.desc")
    suspend fun discoverMovies(@Query("api_key") apiKey: String) : DiscoverMoviesResult

    @GET("discover/movie?include_adult=false&include_video=false&page=1&language=en-US&sort_by=popularity.desc")
    suspend fun discoverMovies(@Query("api_key") apiKey: String,
                       @Query("with_genres") genre:String) : DiscoverMoviesResult

}