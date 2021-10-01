package com.manuelsoft.supermovies2.usecases

import com.manuelsoft.supermovies2.model.PopularMovie
import com.manuelsoft.supermovies2.repository.Repository
import javax.inject.Inject

class LoadSelectedPopularMovieUseCase @Inject constructor(val repository: Repository) {

    operator fun invoke(): PopularMovie {
        return repository.loadSelectedPopularMovie()
    }

}