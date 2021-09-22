package com.manuelsoft.supermovies2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manuelsoft.supermovies2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var rvGenresAdapter: RVGenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        createViewModel()
        createRVGenresAdapter()
        setupRvGenres()
        setDataRVGenresAdapter()
        setOpenFavorites()
        setupBtnShow()
    }

    private fun setupBtnShow() {
        binding.btnShow.setOnClickListener {
            viewModel.loadGenres()
        }
    }

    private fun setOpenFavorites() {
        rvGenresAdapter.setOpenFavorites {
            val intent = Intent(this, GenreActivity::class.java)
            viewModel.saveGenre(it)
            startActivity(intent)
        }
    }

    private fun setDataRVGenresAdapter() {
        viewModel.genres.observe(this, { t ->
            rvGenresAdapter.setData(t)
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
        setSupportActionBar(binding.toolbar)
    }
}