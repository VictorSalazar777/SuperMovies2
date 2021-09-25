package com.manuelsoft.supermovies2.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu.NONE
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.ActivityMainBinding
import com.manuelsoft.supermovies2.model.Genre
import com.manuelsoft.supermovies2.model.PopularMovie
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var rvGenresAdapter: RVGenresAdapter
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
        setLoadMovieSelected()
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
        binding.rvGenres.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity,
                    2, RecyclerView.VERTICAL,
                    false)
            adapter = rvGenresAdapter
        }
    }

    private fun createGenresRecyclerViewAdapter() {
        rvGenresAdapter = RVGenresAdapter()
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
        if (rvGenresAdapter.itemCount > 0) {
            rvGenresAdapter.removeDataWithoutNotify()
            rvGenresAdapter.notifyDataChanged()
        }
        rvGenresAdapter.setData(popularMovies)
        rvGenresAdapter.notifyDataChanged()

    }

    private fun setLoadMovieSelected() {
        rvGenresAdapter.setOpenSelectedMovie {
            viewModel.saveSelectedPopularMovie(it)
            startGenreActivity()
        }
    }

    private fun startGenreActivity() {
        val intent = Intent(this, GenreActivity::class.java)
        startActivity(intent)
    }

}