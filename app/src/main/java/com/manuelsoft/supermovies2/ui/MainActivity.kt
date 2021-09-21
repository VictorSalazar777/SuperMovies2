package com.manuelsoft.supermovies2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.manuelsoft.supermovies2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val genresView = findViewById<RecyclerView>(R.id.rv_genres)
        val rvGenresAdapter = RVGenresAdapter()
        genresView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = rvGenresAdapter
        }

        val viewModel = Injector.getMainActivityViewModel(this)
        viewModel.genres.observe(this, { t ->
            rvGenresAdapter.setData(t)
        })

        val btn = findViewById<MaterialButton>(R.id.btn_show)
        btn.setOnClickListener {
            viewModel.loadGenres()
        }

    }
}