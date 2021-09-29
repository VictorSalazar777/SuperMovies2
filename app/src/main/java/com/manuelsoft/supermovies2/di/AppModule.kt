package com.manuelsoft.supermovies2.di

import android.content.Context
import com.manuelsoft.supermovies2.R
import com.manuelsoft.supermovies2.network.RetrofitProvider
import com.manuelsoft.supermovies2.network.RetrofitService
import com.manuelsoft.supermovies2.network.RetrofitServiceImpl
import com.manuelsoft.supermovies2.repository.Repository
import com.manuelsoft.supermovies2.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(@ApplicationContext context: Context): RetrofitService {
        val moviesDbApi =
            RetrofitProvider.getRetrofitService(context.getString(R.string.base_url))
        val apiKey = context.getString(R.string.themoviedb_api_key)
        return RetrofitServiceImpl(moviesDbApi, apiKey)
    }

    @Provides
    @Singleton
    fun provideRepository(retrofitService: RetrofitService): Repository {
        return RepositoryImpl(retrofitService)
    }

}