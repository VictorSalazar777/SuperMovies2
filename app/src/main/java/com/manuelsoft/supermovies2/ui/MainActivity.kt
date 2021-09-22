package com.manuelsoft.supermovies2.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        val rvGenresAdapter = RVGenresAdapter()
        binding.rvGenres.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = rvGenresAdapter
        }

        val viewModel = Injector.getInjector(this).getMainActivityViewModel(this)
        viewModel.genres.observe(this, { t ->
            rvGenresAdapter.setData(t)
        })
        rvGenresAdapter.setOpenFavorites {
            val intent = Intent(this, GenreActivity::class.java)
            viewModel.saveGenre(it)
            startActivity(intent)
        }

        binding.btnShow.setOnClickListener {
            viewModel.loadGenres()
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}