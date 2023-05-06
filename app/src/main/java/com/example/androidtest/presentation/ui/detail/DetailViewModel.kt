package com.example.androidtest.presentation.ui.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.usecase.CacheDetailTrendingMovie
import com.example.domain.usecase.GetLocalDetailTrendingMovie
import com.example.domain.usecase.GetRemoteDetailTrendingMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getRemoteDetailTrendingMovies: GetRemoteDetailTrendingMovie, private val getLocalDetailTrendingMovies: GetLocalDetailTrendingMovie ,private val cacheDetailTrendingMovie: CacheDetailTrendingMovie):ViewModel()
{
    private val _trendingMovieDetail: MutableStateFlow<DetailMovieResponse?> = MutableStateFlow(null)

    val trendingMovieDetail: StateFlow<DetailMovieResponse?> = _trendingMovieDetail


    fun getTrendingMovieDetail(apiKey:String ,movieId:Long)
    {
        viewModelScope.launch {
            try {
                _trendingMovieDetail.value = getRemoteDetailTrendingMovies(apiKey ,movieId)
            }catch (ex:Exception)
            {
                _trendingMovieDetail.value = getLocalDetailTrendingMovies(movieId)
            }

        }
    }

    fun insertDetailTrendingMovie(detailMovieResponse: DetailMovieResponse)
    {
        viewModelScope.launch {
            cacheDetailTrendingMovie.invoke(detailMovieResponse)
        }
    }


}