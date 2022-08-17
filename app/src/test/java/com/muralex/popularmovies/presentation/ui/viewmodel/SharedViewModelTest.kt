package com.muralex.popularmovies.presentation.ui.viewmodel

import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.domain.usecases.GetMoviesUseCase
import com.muralex.popularmovies.domain.usecases.UpdateMoviesUseCase
import com.muralex.popularmovies.utils.BaseUnitTest
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class SharedViewModelTest : BaseUnitTest() {

    private lateinit var SUT: SharedViewModel
    private val articlesList = mock<List<Article>>()
    private val expected = Resource.success(articlesList)
    private val getNewsUseCase = mockk<GetMoviesUseCase>()
    private val updateUseCase: UpdateMoviesUseCase = mockk()
    private val expectedError = Resource.error("msg", null)


    @Test
    fun getArticles_invoke_getUseCase() {
        mockSuccessfulCase()
        SUT.getArticles()
        coVerify { getNewsUseCase.invoke() }
    }

    @Test
    fun updateArticles_invoke_updateUseCase()  {
        mockSuccessfulCase()

        SUT.updateArticles()

        coVerify {
            delay(800)
            updateUseCase.invoke()
        }
    }

    @Test
    fun startRefresh_onStart_isTrue() {
        mockSuccessfulCase()
        assertThat(SUT.startRefresh).isTrue()
    }

    @Test
    fun startRefresh_afterGetArticles_isFalse() {
        mockSuccessfulCase()
        SUT.getArticles()
        assertThat(SUT.startRefresh).isFalse()
    }

    @Test
    fun startRefresh_afterUpdateArticles_isFalse() = runTest{
        mockSuccessfulCase()
        SUT.updateArticles()
        assertThat(SUT.startRefresh).isFalse()
    }


    private fun mockSuccessfulCase() {
        coEvery { getNewsUseCase.invoke() } returns expected
        coEvery { updateUseCase() } returns expected
        SUT = SharedViewModel(getNewsUseCase, updateUseCase)
    }

    private fun mockErrorCase() {
        coEvery { getNewsUseCase() } returns expectedError
        coEvery { updateUseCase() } returns expectedError
        SUT = SharedViewModel(getNewsUseCase, updateUseCase)
    }



}