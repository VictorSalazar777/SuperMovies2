package com.manuelsoft.supermovies2.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.model.Genre

class RVGenresAdapter : RecyclerView.Adapter<RVGenresAdapter.MyViewHolder>() {

    val TAG = RVGenresAdapter::class.java.name

    private lateinit var onGenreClick: (Genre) -> Unit
    private var genres: List<Genre> = emptyList()

    fun setData(genres: List<Genre>) {
        this.genres = genres
        for (i in 0..genres.size) {
            notifyItemChanged(i)
        }

        Log.d(TAG, genres.toString())
    }

    fun setOpenFavorites(onGenreClick: (Genre) -> Unit) {
        this.onGenreClick = onGenreClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.genre = genres[position]
        holder.txtView.text = genres[position].name
        holder.itemView.setOnClickListener {
            onGenreClick(holder.genre)
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        lateinit var genre: Genre
        val txtView: TextView = itemView.findViewById(R.id.tv_item)
    }

}
