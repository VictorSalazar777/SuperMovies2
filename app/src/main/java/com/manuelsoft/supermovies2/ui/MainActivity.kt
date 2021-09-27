package com.manuelsoft.supermovies2.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu.NONE
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
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
        addFragmentMenu(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        createViewModel()
        setupNavigationView()
        observeGenresFromViewModel()
        viewModel.loadGenres()
        listenOnBackStateChanged()
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
            return
        }
        super.onBackPressed()
    }

    private fun listenOnBackStateChanged() {
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = getCurrentFragment()
            if (currentFragment is FragmentMenu) {
                showMenuNavigationIcon()
                unlockNavigationDrawer()
                openNavigationDrawerOnMenuIconClick()
                return@addOnBackStackChangedListener
            }
            if (currentFragment is FragmentMovie) {
                lockNavigationDrawer()
                showArrowBackNavigationIcon()
                navigateUpOnArrowBackIconClick()
            }
        }
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.fragment_menu_container_view)
    }

    private fun lockNavigationDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun unlockNavigationDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun showMenuNavigationIcon() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)
    }

    private fun showArrowBackNavigationIcon() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
    }

    private fun clearNavigationIcon() {
        binding.toolbar.setNavigationIcon(null)
    }

    private fun openNavigationDrawerOnMenuIconClick() {
        binding.toolbar.setNavigationOnClickListener {
            openDrawer()
        }
    }

    private fun navigateUpOnArrowBackIconClick() {
        binding.toolbar.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
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

    private fun addFragmentMenu(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .commit {
                    setReorderingAllowed(true)
                    add(R.id.fragment_menu_container_view, FragmentMenu::class.java, null)
                }
        }
    }
}