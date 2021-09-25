package com.manuelsoft.supermovies2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.manuelsoft.supermovies2.databinding.ListItemBinding
import com.manuelsoft.supermovies2.model.PopularMovie
import java.util.*

class RVPopularMoviesAdapter : RecyclerView.Adapter<RVPopularMoviesAdapter.MyViewHolder>() {

    val TAG = RVPopularMoviesAdapter::class.java.name

    private lateinit var openSelectedMovie: (PopularMovie) -> Unit
    private lateinit var loadSmallImage: (String, ListItemBinding) -> Unit
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

    fun setOpenSelectedMovie(openSelectedMovie: (PopularMovie) -> Unit) {
        this.openSelectedMovie = openSelectedMovie
    }

    fun setLoadSmallImage(loadSmallImage: (String, ListItemBinding) -> Unit) {
        this.loadSmallImage = loadSmallImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = ListItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(popularMovies[position], loadSmallImage, openSelectedMovie)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    class MyViewHolder(private val listItemBinding: ListItemBinding) : ViewHolder(listItemBinding.root) {

        private lateinit var popularMovie: PopularMovie

        fun setData(popularMovie: PopularMovie,
                    loadSmallImage: (String, ListItemBinding) -> Unit,
                    openSelectedMovie: (PopularMovie) -> Unit) {
            this.popularMovie = popularMovie
            listItemBinding.tvName.text = popularMovie.title
            loadSmallImage(popularMovie.posterPath, listItemBinding)
            itemView.setOnClickListener {
                openSelectedMovie(this.popularMovie)
            }
        }
    }

}
