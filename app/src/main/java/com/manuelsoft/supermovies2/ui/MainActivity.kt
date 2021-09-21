package com.manuelsoft.supermovies2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.manuelsoft.supermovies2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = Injector.getMainActivityViewModel(this)

        viewModel.genres.observe(this, { t ->
            Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
        })

        val btn = findViewById<MaterialButton>(R.id.btn_show)
        btn.setOnClickListener {
            viewModel.loadGenres()
        }
    }
}