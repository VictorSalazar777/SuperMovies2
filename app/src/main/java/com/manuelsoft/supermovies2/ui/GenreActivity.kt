package com.manuelsoft.supermovies2.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.manuelsoft.supermovies2.databinding.ActivityGenreBinding
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMoviesByGenre

class GenreActivity : AppCompatActivity() {

    val TAG = GenreActivity::class.java.name
    private lateinit var binding: ActivityGenreBinding
    private lateinit var popularMovie: PopularMoviesByGenre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = Injector.getInjector(this).getGenreActivityViewModel(this)
        viewModel.popularMovies.observe(this, {
            popularMovie = it[0]
            binding.apply {
                tvMovieName.text = popularMovie.title
                tvMovieOverview.text = popularMovie.overview
                tvReleaseDate.text = popularMovie.releaseDate
                tvOriginalLanguage.text = popularMovie.originalLanguage
            }
        })

        viewModel.loadFavoriteMoviesByGenre(viewModel.loadGenre().id.toString())
    }
}