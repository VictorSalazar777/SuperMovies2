package com.manuelsoft.supermovies2.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.ActivityMainBinding
import com.manuelsoft.supermovies2.databinding.ListItemBinding
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMovie
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var rvPopularMoviesAdapter: RVPopularMoviesAdapter
    private lateinit var navGenreMenuItems: MutableList<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        createViewModel()
        createGenresRecyclerViewAdapter()
        setupGenresRecyclerView()
        setupNavigationView()
        observeGenresFromViewModel()
        setLoadSmallImage()
        setOpenSelectedMovie()
        viewModel.loadGenres()
        observePopularMoviesFromViewModel()
    }

    private fun setupNavigationView() {
        binding.apply {
            navigationView.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                hideDrawer()
                true
            }
        }
    }

    private fun observeGenresFromViewModel() {
        viewModel.genres.observe(this, { t ->
            showGenres(t)
        })
    }

    private fun showGenres(genres: List<Genre>) {
        navGenreMenuItems = LinkedList()
        genres.forEach { genre ->
            val item = binding.navigationView.menu.add(NONE, genre.id, NONE, genre.name)
            navGenreMenuItems.add(item)
            MenuItemCompat.setContentDescription(item, genre.name)
            item.setOnMenuItemClickListener {
                Log.d(TAG, "${MenuItemCompat.getContentDescription(it)}, ${it.itemId}")
                hideDrawer()
                viewModel.loadFavoriteMoviesByGenre(it.itemId.toString())
                true
            }
        }
        viewModel.loadFavoriteMoviesByGenre(genres[0].id.toString())
    }

    private fun setupGenresRecyclerView() {
        binding.rvPopularMovies.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity,
                    2, RecyclerView.VERTICAL,
                    false)
            adapter = rvPopularMoviesAdapter
            addItemDecoration(DividerItemDecoration(this.context, GridLayoutManager.VERTICAL))
            addItemDecoration(DividerItemDecoration(this.context, GridLayoutManager.HORIZONTAL))
        }
    }

    private fun createGenresRecyclerViewAdapter() {
        rvPopularMoviesAdapter = RVPopularMoviesAdapter()
    }

    private fun createViewModel() {
        viewModel = Injector.getInjector(this).getMainActivityViewModel(this)
    }

    private fun setupToolbar() {
        binding.apply {
            setSupportActionBar(toolbar)
            toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)
            toolbar.setNavigationOnClickListener {
                openDrawer()
            }
        }
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun hideDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))  {
            hideDrawer()
        }
        else {
            super.onBackPressed()
        }
    }

    private fun observePopularMoviesFromViewModel() {
        viewModel.popularMovies.observe(this, {
            showPopularMoviesOfTheSelectedGenre(it)
        })
    }

    private fun showPopularMoviesOfTheSelectedGenre(popularMovies : List<PopularMovie>) {
        if (rvPopularMoviesAdapter.itemCount > 0) {
            rvPopularMoviesAdapter.removeDataWithoutNotify()
            rvPopularMoviesAdapter.notifyDataChanged()
        }
        rvPopularMoviesAdapter.setData(popularMovies)
        rvPopularMoviesAdapter.notifyDataChanged()

    }

    private fun setLoadSmallImage() {
        rvPopularMoviesAdapter.setLoadSmallImage { path, listItemBinding ->
            run {
                getPoster(path, listItemBinding)
            }
        }
    }

    private fun getPoster(path: String, listItemBinding: ListItemBinding) {
        val url = "https://image.tmdb.org/t/p/w92$path"
        listItemBinding.progressCircularPosterSmall.visibility = View.VISIBLE
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    listItemBinding.progressCircularPosterSmall.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    listItemBinding.progressCircularPosterSmall.visibility = View.GONE
                    return false
                }

            })
            .into(listItemBinding.ivItemPosterSmall)
    }

    private fun setOpenSelectedMovie() {
        rvPopularMoviesAdapter.setOpenSelectedMovie {
            viewModel.saveSelectedPopularMovie(it)
            startGenreActivity()
        }
    }

    private fun startGenreActivity() {
        val intent = Intent(this, GenreActivity::class.java)
        startActivity(intent)
    }

}