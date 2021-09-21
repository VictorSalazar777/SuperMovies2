package com.manuelsoft.supermovies2.repository

import android.content.Context
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.network.RetrofitServiceImpl

class RepositoryImpl(context: Context) : Repository {

    private val retrofitService = RetrofitServiceImpl(context)

    override suspend fun getGenres(): List<Genre> {
        return retrofitService.getGenres().genreList
    }
}