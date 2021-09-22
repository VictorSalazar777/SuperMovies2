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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        val rvGenresAdapter = RVGenresAdapter()
        val genresView = findViewById<RecyclerView>(R.id.rv_genres)
        genresView.apply {
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

        val btn = findViewById<MaterialButton>(R.id.btn_show)
        btn.setOnClickListener {
            viewModel.loadGenres()
        }

    }

    private fun setupToolbar() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}