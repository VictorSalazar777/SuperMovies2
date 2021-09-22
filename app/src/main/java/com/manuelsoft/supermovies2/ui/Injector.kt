package com.manuelsoft.supermovies2.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.manuelsoft.supermovies2.repository.Repository
import com.manuelsoft.supermovies2.repository.RepositoryImpl

class Injector(private var repository: Repository) {

    fun getMainActivityViewModel(activity: MainActivity): MainActivityViewModel {
        val viewModelFactory = ViewModelFactory(repository)
        return ViewModelProvider(activity, viewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    fun getGenreActivityViewModel(activity: GenreActivity): GenreActivityViewModel {
        val viewModelFactory = ViewModelFactory(repository)
        return ViewModelProvider(activity, viewModelFactory)
            .get(GenreActivityViewModel::class.java)
    }

    companion object {
        private var injector: Injector? = null

        @JvmStatic
        fun getInjector(context: Context): Injector {
            if (injector == null) {
                injector = Injector(RepositoryImpl(context.applicationContext))
            }
            return injector as Injector
        }
    }
}