package com.manuelsoft.supermovies2.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manuelsoft.supermovies2.databinding.ActivityGenreBinding
import com.manuelsoft.supermovies2.model.PopularMovie


class GenreActivity : AppCompatActivity() {

    val TAG = GenreActivity::class.java.name
    private lateinit var binding: ActivityGenreBinding
    private lateinit var popularMovie: PopularMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()

        val viewModel = Injector.getInjector(this).getGenreActivityViewModel(this)
        popularMovie = viewModel.loadSelectedPopularMovie()

        getPoster(popularMovie.posterPath)

        binding.apply {
            tvMovieName.text = popularMovie.title
            tvMovieOverview.text = popularMovie.overview
            tvReleaseDate.text = popularMovie.releaseDate
            tvOriginalLanguage.text = popularMovie.originalLanguage
        }
    }

    private fun getPoster(path: String) {
        val url = "https://image.tmdb.org/t/p/w300$path"
        binding.progressCircular.visibility = View.VISIBLE
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressCircular.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressCircular.visibility = View.GONE
                    return false
                }

            })
            .into(binding.ivPoster)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar2)
    }


}