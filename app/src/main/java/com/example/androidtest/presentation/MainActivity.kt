package com.example.androidtest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.BuildConfig
import com.example.androidtest.R
import com.example.androidtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by viewModels()

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