package com.manuelsoft.supermovies2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuelsoft.supermovies2.repository.Repository

@SuppressWarnings("unchecked")
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(FragmentMovieViewModel::class.java)) {
            return FragmentMovieViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown class name: " + modelClass.name)
    }
}