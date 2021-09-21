package com.manuelsoft.supermovies2.repository

import com.manuelsoft.supermovies2.model.Genre

interface Repository {

    suspend fun getGenres() : List<Genre>

}