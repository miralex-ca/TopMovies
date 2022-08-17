package com.muralex.popularmovies.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.domain.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private lateinit var SUT: GetMoviesUseCase
    private val repository = mock<MovieRepository>()
    private val mapper: ArticleDomainToUiMapper = mock()
    private val expectedData: Resource<List<Article>> = mock()

    @Before
    fun setUp() {
        SUT =  GetMoviesUseCase(repository, mapper)
    }

    @Test
    fun getNewsUseCase_invoke_repositoryGetNews() = runTest {
        SUT.invoke()
        verify(repository, times(1)).getMovies()
    }


    @Test
    fun getNewsUseCase_invoke_expectedDataFromRepository() = runTest {

        whenever(mapper.mapFromEntity(any())).thenReturn(
            expectedData
        )

        whenever(repository.getMovies()).thenReturn(
            expectedData
        )

        val item = SUT.invoke()
        assertThat(item).isEqualTo(expectedData)
    }

}