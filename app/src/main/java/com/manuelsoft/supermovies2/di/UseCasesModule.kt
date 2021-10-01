package com.manuelsoft.supermovies2.di

import com.manuelsoft.supermovies2.repository.Repository
import com.manuelsoft.supermovies2.usecases.LoadFavoriteMoviesByGenreUseCase
import com.manuelsoft.supermovies2.usecases.LoadGenresUseCase
import com.manuelsoft.supermovies2.usecases.LoadSelectedPopularMovieUseCase
import com.manuelsoft.supermovies2.usecases.SaveSelectedPopularMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideLoadGenresUseCase(repository: Repository): LoadGenresUseCase {
        return LoadGenresUseCase(repository)
    }

    @Provides
    fun provideLoadFavoriteMoviesByGenreUseCase(repository: Repository): LoadFavoriteMoviesByGenreUseCase {
        return LoadFavoriteMoviesByGenreUseCase(repository)
    }

    @Provides
    fun provideLoadSelectedPopularMovieUseCase(repository: Repository): LoadSelectedPopularMovieUseCase {
        return LoadSelectedPopularMovieUseCase(repository)
    }

    @Provides
    fun provideSaveSelectedPopularMovieUseCase(repository: Repository): SaveSelectedPopularMovieUseCase {
        return SaveSelectedPopularMovieUseCase(repository)
    }

}