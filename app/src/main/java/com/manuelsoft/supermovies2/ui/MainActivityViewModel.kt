package com.manuelsoft.supermovies2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository : Repository) : ViewModel() {

    val TAG = MainActivityViewModel::class.java.name

    private val _genres = MutableLiveData<List<Genre>>()

    val genres : LiveData<List<Genre>>
        get() = _genres

    fun loadGenres() : Job {
        return viewModelScope.launch {
            try {
                _genres.value = repository.getGenres()
            } catch (error: Throwable) {
                Log.d(TAG, "$error")
            }
        }
    }


}