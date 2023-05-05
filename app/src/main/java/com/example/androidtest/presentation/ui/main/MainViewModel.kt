package com.example.androidtest.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import com.example.domain.usecase.CacheTrendingMovie
import com.example.domain.usecase.GetLocalTrendingMovies
import com.example.domain.usecase.GetRemoteTrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRemoteTrendingMovies: GetRemoteTrendingMovies, private val getLocalTrendingMovies: GetLocalTrendingMovies, private val cacheTrendingMovie: CacheTrendingMovie):ViewModel() {

    private val _trendingMovies:MutableStateFlow<TrendingMoviesResponse?> = MutableStateFlow(null)

    val trendingMovies: StateFlow<TrendingMoviesResponse?> = _trendingMovies

    fun getTrendingMovies(apiKey:String)
    {
        viewModelScope.launch {
            try {
                _trendingMovies.value = getRemoteTrendingMovies(apiKey)
            }catch (ex:Exception)
            {
                _trendingMovies.value =  TrendingMoviesResponse(results = getLocalTrendingMovies())
            }

        }
    }

    fun insertTrendingMovie(movie: Movie)
    {
        viewModelScope.launch {
            cacheTrendingMovie(movie)
        }
    }

}