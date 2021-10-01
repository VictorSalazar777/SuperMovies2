package com.manuelsoft.supermovies2.usecases

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manuelsoft.supermovies2.model.PopularMovie
import com.manuelsoft.supermovies2.repository.Repository
import javax.inject.Inject


class LoadFavoriteMoviesByGenreUseCase @Inject constructor(val repository: Repository) {

    companion object {
        val TAG: String = LoadFavoriteMoviesByGenreUseCase::class.java.name
    }

    suspend operator fun invoke(popularMovies: MutableLiveData<List<PopularMovie>>, genreId: String) {
        try {
            popularMovies.value = repository.popularMoviesByGenre(genreId)
        } catch (e: Exception) {
            Log.d(LoadGenresUseCase.TAG, "$e")
        }
    }


}