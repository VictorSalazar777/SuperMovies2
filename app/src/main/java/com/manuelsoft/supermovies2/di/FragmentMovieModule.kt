package com.manuelsoft.supermovies2.di

import com.manuelsoft.supermovies2.ui.main.RVPopularMoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentMovieModule {

    @Provides
    fun provideRVPopularMoviesAdapter(): RVPopularMoviesAdapter {
        return RVPopularMoviesAdapter()
    }
}