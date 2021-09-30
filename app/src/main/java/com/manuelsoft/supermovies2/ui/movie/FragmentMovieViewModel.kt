package com.manuelsoft.supermovies2.ui.movie

import com.manuelsoft.supermovies2.model.PopularMovie
import com.manuelsoft.supermovies2.repository.Repository
import com.manuelsoft.supermovies2.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentMovieViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    fun loadSelectedPopularMovie(): PopularMovie {
        return repository.loadSelectedPopularMovie()
    }

}