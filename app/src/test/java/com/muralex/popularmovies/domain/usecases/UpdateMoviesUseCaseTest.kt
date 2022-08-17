package com.muralex.popularmovies.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.domain.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UpdateMoviesUseCaseTest {

    private lateinit var SUT : UpdateMoviesUseCase
    private val repository = mockk<MovieRepository>()
    private val mapper: ArticleDomainToUiMapper = mockk()
    private val expectedData: Resource<List<Article>> = mockk()

    @Before
    fun setUp() {
        SUT =  UpdateMoviesUseCase(repository, mapper)
        coEvery { mapper.mapFromEntity(any()) } returns expectedData
        coEvery { repository.updateMovies() } returns expectedData
    }

    @Test
    fun updateNewsUseCase_invoke_repositoryGetNews() = runTest {
        SUT.invoke()
        coVerify {repository.updateMovies()}
    }

    @Test
    fun getNewsUseCase_invoke_expectedDataFromRepository() = runTest {
        val item = SUT.invoke()
        assertThat(item).isEqualTo(expectedData)
    }

}