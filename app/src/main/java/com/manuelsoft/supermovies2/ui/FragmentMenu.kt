package com.manuelsoft.supermovies2.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.databinding.FragmentMenuBinding
import com.manuelsoft.supermovies2.databinding.ListItemBinding
import com.manuelsoft.supermovies2.model.PopularMovie


class FragmentMenu : Fragment(R.layout.fragment_menu) {

    private var binding: FragmentMenuBinding? = null
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var rvPopularMoviesAdapter: RVPopularMoviesAdapter

    companion object {
        @JvmStatic
        val TAG: String = FragmentMenu::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel()
    }

    private fun getViewModel() {
        viewModel = Injector.getInjector(requireContext())
            .getMainActivityViewModel(requireActivity() as MainActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        createGenresRecyclerViewAdapter()
        setupGenresRecyclerView()
        setLoadSmallImage()
        setOpenSelectedMovie()
        observePopularMoviesFromViewModel()
    }

    private fun createGenresRecyclerViewAdapter() {
        rvPopularMoviesAdapter = RVPopularMoviesAdapter()
    }

    private fun setupGenresRecyclerView() {
        binding?.rvPopularMovies?.apply {
            layoutManager =
                GridLayoutManager(
                    this.context,
                    2, RecyclerView.VERTICAL,
                    false
                )
            adapter = rvPopularMoviesAdapter
            addItemDecoration(DividerItemDecoration(this.context, GridLayoutManager.VERTICAL))
            addItemDecoration(DividerItemDecoration(this.context, GridLayoutManager.HORIZONTAL))
        }
    }

    private fun observePopularMoviesFromViewModel() {
        viewModel.popularMovies.observe(viewLifecycleOwner, {
            showPopularMoviesOfTheSelectedGenre(it)
        })
    }

    private fun showPopularMoviesOfTheSelectedGenre(popularMovies: List<PopularMovie>) {
        if (rvPopularMoviesAdapter.itemCount > 0) {
            rvPopularMoviesAdapter.removeDataWithoutNotify()
            rvPopularMoviesAdapter.notifyDataChanged()
        }
        rvPopularMoviesAdapter.setData(popularMovies)
        rvPopularMoviesAdapter.notifyDataChanged()

    }

    private fun setLoadSmallImage() {
        rvPopularMoviesAdapter.setLoadSmallImage { path, listItemBinding ->
            run {
                getPoster(path, listItemBinding)
            }
        }
    }

    private fun getPoster(path: String, listItemBinding: ListItemBinding) {
        val url = "https://image.tmdb.org/t/p/w92$path"
        listItemBinding.progressCircularPosterSmall.visibility = View.VISIBLE
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    listItemBinding.progressCircularPosterSmall.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    listItemBinding.progressCircularPosterSmall.visibility = View.GONE
                    return false
                }

            })
            .into(listItemBinding.ivItemPosterSmall)
    }

    private fun setOpenSelectedMovie() {
        rvPopularMoviesAdapter.setOpenSelectedMovie {
            viewModel.saveSelectedPopularMovie(it)
            startFragmentMovie()
        }
    }

    private fun startFragmentMovie() {
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                android.R.animator.fade_in,
                android.R.animator.fade_out,
                android.R.animator.fade_in,
                android.R.animator.fade_out
            )
            replace(R.id.fragment_menu_container_view, FragmentMovie::class.java, null, null)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}