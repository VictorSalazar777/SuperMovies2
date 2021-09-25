package com.manuelsoft.supermovies2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.manuelsoft.supermovies2.databinding.ListItemBinding
import com.manuelsoft.supermovies2.model.PopularMovie
import java.util.*

class RVGenresAdapter : RecyclerView.Adapter<RVGenresAdapter.MyViewHolder>() {

    val TAG = RVGenresAdapter::class.java.name

    private lateinit var onSelectedMovieClick: (PopularMovie) -> Unit
    private var popularMovies: MutableList<PopularMovie> = LinkedList()

    fun setData(basicPopularMovies: List<PopularMovie>) {
        this.popularMovies = basicPopularMovies.toMutableList()
    }

    fun notifyDataChanged() {
        for (i in 0..popularMovies.size) {
            notifyItemChanged(i)
        }
    }

    fun removeDataWithoutNotify() {
        popularMovies.clear()
    }

    fun removeDataAndNotify() {
        removeDataWithoutNotify()
        val lastIndex = popularMovies.lastIndex
        notifyItemRangeRemoved(0, lastIndex)
    }

    fun setOpenSelectedMovie(onMovieClick: (PopularMovie) -> Unit) {
        this.onSelectedMovieClick = onMovieClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = ListItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(popularMovies[position])
        holder.itemView.setOnClickListener {
            onSelectedMovieClick(popularMovies[position])
        }
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    class MyViewHolder(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        private var id = 0

        fun setData(popularMovie: PopularMovie) {
            binding.tvName.text = popularMovie.title
            id = popularMovie.id
        }
    }

}
