package com.manuelsoft.supermovies2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMovie
import com.manuelsoft.supermovies2.repository.Repository
import com.manuelsoft.supermovies2.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    companion object {
        val TAG: String = MainActivityViewModel::class.java.name
    }

    private val _genres = MutableLiveData<List<Genre>>()

    val genres: LiveData<List<Genre>>
        get() = _genres

    fun loadGenres(): Job {
        return viewModelScope.launch {
            try {
                _genres.value = repository.getGenres()
            } catch (error: Throwable) {
                Log.d(TAG, "$error")
            }
        }
    }

    private val _popularMovies = MutableLiveData<List<PopularMovie>>()

    val popularMovies: LiveData<List<PopularMovie>>
        get() {
            return _popularMovies
        }

    fun loadFavoriteMoviesByGenre(genreId: String): Job {
        return viewModelScope.launch {
            try {
                _popularMovies.value = repository.popularMoviesByGenre(genreId)
            } catch (e: Exception) {
                Log.d(TAG, "$e")
            }
        }
    }

    fun saveSelectedPopularMovie(popularMovie: PopularMovie) {
        repository.saveSelectedPopularMovie(popularMovie)
    }

}