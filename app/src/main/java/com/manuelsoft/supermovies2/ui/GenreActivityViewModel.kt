package com.manuelsoft.supermovies2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMoviesByGenre
import com.manuelsoft.supermovies2.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


class GenreActivityViewModel(private val repository: Repository) : BaseViewModel() {

    val TAG = GenreActivityViewModel::class.java.name
    private val _popularMovies = MutableLiveData<List<PopularMoviesByGenre>>()

    val popularMovies : LiveData<List<PopularMoviesByGenre>>
        get() {
            return _popularMovies
        }

    fun loadGenre() : Genre {
        return repository.loadGenre()
    }

    fun loadFavoriteMoviesByGenre(genreId : String) : Job {
        return viewModelScope.launch {
            try {
                _popularMovies.value = repository.popularMoviesByGenre(genreId)
            } catch (e: Exception) {
                Log.d(TAG, "$e")
            }
        }
    }

}