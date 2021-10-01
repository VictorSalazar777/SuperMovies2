package com.manuelsoft.supermovies2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMovie
import com.manuelsoft.supermovies2.ui.BaseViewModel
import com.manuelsoft.supermovies2.usecases.LoadFavoriteMoviesByGenreUseCase
import com.manuelsoft.supermovies2.usecases.LoadGenresUseCase
import com.manuelsoft.supermovies2.usecases.SaveSelectedPopularMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loadGenresUseCase: LoadGenresUseCase,
    private val loadFavoriteMoviesByGenreUseCase: LoadFavoriteMoviesByGenreUseCase,
    private val saveSelectedPopularMovieUseCase: SaveSelectedPopularMovieUseCase
    ) : BaseViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    private val _popularMovies = MutableLiveData<List<PopularMovie>>()

    val genres: LiveData<List<Genre>>
        get() = _genres

    val popularMovies: LiveData<List<PopularMovie>>
        get() {
            return _popularMovies
        }

    fun loadGenres(): Job {
        return viewModelScope.launch {
            loadGenresUseCase(_genres)
        }
    }

    fun loadFavoriteMoviesByGenre(genreId: String): Job {
        return viewModelScope.launch {
            loadFavoriteMoviesByGenreUseCase(_popularMovies, genreId)
        }
    }

    fun saveSelectedPopularMovie(popularMovie: PopularMovie) {
        saveSelectedPopularMovieUseCase(popularMovie)
    }

}