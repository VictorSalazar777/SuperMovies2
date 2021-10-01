package com.manuelsoft.supermovies2.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.repository.Repository
import javax.inject.Inject


class LoadGenresUseCase @Inject constructor(val repository: Repository) {

    companion object {
        val TAG: String = LoadGenresUseCase::class.java.name
    }

    suspend operator fun invoke(genres: MutableLiveData<List<Genre>>) {
        try {
            genres.value = repository.getGenres()
        } catch (error: Throwable) {
            Log.d(LoadFavoriteMoviesByGenreUseCase.TAG, "$error")
        }
    }
}