package com.muralex.popularmovies.presentation.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.common.data.Status
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.domain.usecases.GetMoviesUseCase
import com.muralex.popularmovies.domain.usecases.UpdateMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    private var _startRefresh = true
    val startRefresh: Boolean
        get() = _startRefresh


    private var _viewState = mutableStateOf<ViewState>( ViewState.Loading )
    val viewState: State<ViewState>
    get() = _viewState


    fun getNews() {
        getData(GET_TYPE)
    }

    fun updateNews() {
        getData(UPDATE_TYPE)
    }

    private fun getData(type: Int) {
        _startRefresh = false
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = ViewState.Loading

            try {
                val response = getDataFromUseCase(type)
                when (response.status) {
                    Status.LOADING -> _viewState.value = ViewState.Loading
                    Status.ERROR -> {
                        _viewState.value =  ViewState.ListLoadFailure(response)
                    }
                    Status.SUCCESS -> {
                        if (type == GET_TYPE) _viewState.value =  ViewState.ListLoaded(response)
                        else _viewState.value = ViewState.ListRefreshed(response)
                    }
                }
            } catch (e: Exception) {
                Timber.d(e, "Error fetching data from repository")
                val resource = Resource.error(e.message.toString(), null)
                _viewState.value =  ViewState.ListLoadFailure(resource)
            }
        }
    }

    private suspend fun getDataFromUseCase(type: Int): Resource<List<Article>> {
        return if (type == UPDATE_TYPE) updateMoviesUseCase()
        else getMoviesUseCase()
    }

    private companion object {
        const val UPDATE_TYPE = 1
        const val GET_TYPE = 2
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class ListLoaded(val data: Resource<List<Article>>) : ViewState()
        data class ListRefreshed(val data: Resource<List<Article>>) : ViewState()
        data class ListLoadFailure(val data: Resource<List<Article>>) : ViewState()
    }

}