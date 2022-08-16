package com.muralex.popularmovies.domain.usecases

import com.muralex.popularmovies.domain.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: ArticleDomainToUiMapper
    ) {
    suspend operator fun invoke()  = mapper.mapFromEntity(repository.getMovies())
}