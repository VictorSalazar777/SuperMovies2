package com.manuelsoft.supermovies2.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.manuelsoft.supermovies2.R

class GenreActivity : AppCompatActivity() {

    val TAG = GenreActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)
        val viewModel = Injector.getInjector(this).getGenreActivityViewModel(this)
        viewModel.popularMovies.observe(this, {
            Log.d(TAG, it.toString())
        })
        viewModel.loadFavoriteMoviesByGenre(viewModel.loadGenre().id.toString())
    }
}