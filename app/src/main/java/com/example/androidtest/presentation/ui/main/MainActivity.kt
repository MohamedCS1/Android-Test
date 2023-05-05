package com.example.androidtest.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.BuildConfig
import com.example.androidtest.databinding.ActivityMainBinding
import com.example.androidtest.util.BitmapUtil.getBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var binding:ActivityMainBinding
    lateinit var moviesAdapter: MoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesAdapter = MoviesAdapter()
        initRecyclerView()

        mainViewModel.getTrendingMovies(BuildConfig.API_KEY)

        lifecycleScope.launch {
            mainViewModel.trendingMovies.collect{
                trendingMoviesResponse ->
                Log.d("response" ,trendingMoviesResponse.toString())
                if (trendingMoviesResponse == null){
                    Toast.makeText(this@MainActivity ,"Something wrong please try again" ,Toast.LENGTH_SHORT).show()
                }
                else
                {
                    moviesAdapter.submitTrendingMoviesResponse(trendingMoviesResponse)

                    trendingMoviesResponse.results?.forEach {
                        movie ->
                        val moviePoster = async {getBitmap(this@MainActivity ,"https://image.tmdb.org/t/p/original/${movie.poster_path}"?:"")}
                        mainViewModel.insertTrendingMovie(movie.copy(bitMapPoster = moviePoster.await()))
                        binding.progressLoading.visibility = View.INVISIBLE
                    }

                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewTrendingMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}