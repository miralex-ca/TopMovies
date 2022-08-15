package com.muralex.popularmovies.domain.usecases

import com.muralex.popularmovies.domain.MovieRepository
import javax.inject.Inject

class UpdateMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke()  = repository.updateMovies()
}