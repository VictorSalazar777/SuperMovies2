package com.manuelsoft.supermovies2.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu.NONE
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.commit
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.ActivityMainBinding
import com.manuelsoft.supermovies2.model.Genre
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var navGenreMenuItems: MutableList<MenuItem>

    companion object {
        @JvmStatic
        val TAG: String = MainActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addMenuFragment(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        createViewModel()
        setupNavigationView()
        observeGenresFromViewModel()
        viewModel.loadGenres()
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

    private fun createViewModel() {
        viewModel = Injector.getInjector(this).getMainActivityViewModel(this)
    }

    private fun observeGenresFromViewModel() {
        viewModel.genres.observe(this, { genres ->
            createAndShowGenreItemsOnNavigationMenu(genres)
            loadFirstGenre(genres)
        })
    }

    private fun createAndShowGenreItemsOnNavigationMenu(genres: List<Genre>) {
        navGenreMenuItems = LinkedList()
        genres.forEach { genre ->
            val item = binding.navigationView.menu.add(NONE, genre.id, NONE, genre.name)
            MenuItemCompat.setContentDescription(item, genre.name)
            item.setOnMenuItemClickListener {
                Log.d(TAG, "${MenuItemCompat.getContentDescription(it)}, ${it.itemId}")
                hideDrawer()
                loadSelectedMovie(it.itemId.toString())
                true
            }
            navGenreMenuItems.add(item)
        }
    }

    private fun loadSelectedMovie(id: String) {
        viewModel.loadFavoriteMoviesByGenre(id)
    }


    private fun loadFirstGenre(genres: List<Genre>) {
        viewModel.loadFavoriteMoviesByGenre(genres[0].id.toString())
    }

    private fun addMenuFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_menu_container_view, FragmentMenu::class.java, null)
            }
        }
    }

}