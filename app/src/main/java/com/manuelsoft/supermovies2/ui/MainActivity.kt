package com.manuelsoft.supermovies2.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu.NONE
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name
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
        createRVGenresAdapter()
        setupRvGenres()
        setupNavigationView()
        showRVGenres()
        startGenreActivity()
        setupBtnShow()
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

    private fun setupBtnShow() {
        binding.btnShow.setOnClickListener {
            viewModel.loadGenres()
        }
    }

    private fun startGenreActivity() {
        rvGenresAdapter.setOpenFavorites {
            val intent = Intent(this, GenreActivity::class.java)
            viewModel.saveGenre(it)
            startActivity(intent)
        }
    }

    private fun showRVGenres() {
        viewModel.genres.observe(this, { t ->
            navGenreMenuItems = LinkedList()
            rvGenresAdapter.setData(t)
            t.forEach { genre ->
                val item = binding.navigationView.menu.add(NONE, genre.id, NONE, genre.name)
                navGenreMenuItems.add(item)
                MenuItemCompat.setContentDescription(item, genre.name)
                item.setOnMenuItemClickListener {
                    Log.d(TAG, "${MenuItemCompat.getContentDescription(it)}, ${it.itemId}")
                    hideDrawer()
                    true
                }
            }
        })
    }

    private fun setupRvGenres() {
        binding.rvGenres.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = rvGenresAdapter
        }
    }

    private fun createRVGenresAdapter() {
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
}