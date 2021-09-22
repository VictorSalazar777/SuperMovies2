package com.manuelsoft.supermovies2.ui

import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.repository.Repository


class GenreActivityViewModel(private val repository: Repository) : BaseViewModel() {

    fun loadGenre() : Genre {
        return repository.loadGenre()
    }

}