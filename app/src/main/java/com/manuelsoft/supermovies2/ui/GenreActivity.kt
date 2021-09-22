package com.manuelsoft.supermovies2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.manuelsoft.supermovies2.R

class GenreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)
        val viewModel = Injector.getInjector(this).getGenreActivityViewModel(this)
        Toast.makeText(this, viewModel.loadGenre().toString(), Toast.LENGTH_LONG).show()
    }
}