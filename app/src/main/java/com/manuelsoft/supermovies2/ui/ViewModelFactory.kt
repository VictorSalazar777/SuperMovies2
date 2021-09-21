package com.manuelsoft.supermovies2.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuelsoft.supermovies2.repository.RepositoryImpl

class ViewModelFactory(private val application : Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            val repository = RepositoryImpl(application.applicationContext)
            return MainActivityViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown class name: " + modelClass.name)
    }
}