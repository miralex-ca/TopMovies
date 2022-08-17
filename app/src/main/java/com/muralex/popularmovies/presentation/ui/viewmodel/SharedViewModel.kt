package com.muralex.popularmovies.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.common.data.Status
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.domain.usecases.GetMoviesUseCase
import com.muralex.popularmovies.domain.usecases.UpdateMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private var _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState>
    get() = _viewState

    fun getArticles() {
        getData(GET_TYPE)
    }

    fun updateArticles() {
        getData(UPDATE_TYPE)
    }

    private fun getData(type: Int) {
        _startRefresh = false
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = ViewState.Loading
            if (type == UPDATE_TYPE) delay(700)
            try {
                val response = getDataFromUseCase(type)
                when (response.status) {
                    Status.LOADING -> _viewState.value = ViewState.Loading
                    Status.ERROR -> {
                        _viewState.value =  ViewState.ListLoadFailure(response.message.toString())
                    }
                    Status.SUCCESS -> {
                        if (type == GET_TYPE) _viewState.value =  ViewState.ListLoaded(response)
                        else _viewState.value = ViewState.ListRefreshed(response)
                    }
                }
            } catch (e: Exception) {
                Timber.d(e, "Error fetching data from repository")
                _viewState.value =  ViewState.ListLoadFailure(e.message.toString())
            }
        }
    }

    private val _selectedArticle: MutableStateFlow<Article> = MutableStateFlow(
        Article(0, "", "", "", "", "", "", 0.0)
    )
    val selectedArticle : StateFlow<Article> = _selectedArticle

    fun getSelectedItem(itemId: Int) {

        var list = emptyList<Article>()

        if (_viewState.value is ViewState.ListLoaded) {
            list = (_viewState.value as ViewState.ListLoaded).data.data ?: emptyList()
        }

        if (_viewState.value is ViewState.ListRefreshed) {
            list = (_viewState.value as ViewState.ListRefreshed).data.data ?: emptyList()
        }

        _selectedArticle.value = list.first{ it.id == itemId}
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
        data class ListLoadFailure(val message: String) : ViewState()
    }

}