package com.manuelsoft.supermovies2.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.FragmentMovieBinding
import com.manuelsoft.supermovies2.model.PopularMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMovie : Fragment(R.layout.fragment_movie) {

    private val viewModel : FragmentMovieViewModel by viewModels()
    private var binding: FragmentMovieBinding? = null
    private lateinit var popularMovie: PopularMovie

    companion object {
        val TAG: String = FragmentMovie::class.java.name
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        popularMovie = viewModel.loadSelectedPopularMovie()

        getPoster(popularMovie.posterPath)

        binding?.apply {
            tvMovieName.text = popularMovie.title
            tvMovieOverview.text = popularMovie.overview
            tvReleaseDate.text = popularMovie.releaseDate
            tvOriginalLanguage.text = popularMovie.originalLanguage
        }
    }

    private fun getPoster(path: String) {
        val url = "https://image.tmdb.org/t/p/w300$path"

        binding?.let { binding ->
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
                        Log.e(TAG, e.toString())
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
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}

