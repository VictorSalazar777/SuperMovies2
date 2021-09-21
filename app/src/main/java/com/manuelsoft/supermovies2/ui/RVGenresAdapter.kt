package com.manuelsoft.supermovies2.ui

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuelsoft.supermovies2.model.Genre
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.manuelsoft.supermovies2.R

class RVGenresAdapter : RecyclerView.Adapter<RVGenresAdapter.MyViewHolder>() {

    val TAG = RVGenresAdapter::class.java.name

    var genres : List<Genre> = emptyList()

    fun setData(genres: List<Genre>) {
        this.genres = genres
        for (i in 0..genres.size) {
            notifyItemChanged(i)
        }

        Log.d(TAG, genres.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtView.text = genres[position].name
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val txtView : TextView = itemView.findViewById(R.id.tv_item)

    }

}
