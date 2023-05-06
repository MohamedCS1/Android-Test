package com.example.androidtest.presentation.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.database.CursorWindow
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.BuildConfig
import com.example.androidtest.databinding.ActivityMainBinding
import com.example.androidtest.presentation.ui.detail.DetailActivity

import com.example.domain.models.trendingMovies.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.reflect.Field


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var binding:ActivityMainBinding
    lateinit var moviesAdapter: MoviesAdapter
    @SuppressLint("DiscouragedPrivateApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expandDefaultSize()

        moviesAdapter = MoviesAdapter()
        initRecyclerView()

        mainViewModel.getTrendingMovies(BuildConfig.API_KEY)

        lifecycleScope.launch {
            mainViewModel.trendingMovies.collect{
                trendingMoviesResponse ->

                    trendingMoviesResponse?.let { moviesAdapter.submitTrendingMoviesResponse(it) }

                    trendingMoviesResponse?.results?.forEach {
                        movie ->
                        mainViewModel.insertTrendingMovie(movie)
                        binding.progressLoading.visibility = View.INVISIBLE
                    }

            }
        }


        moviesAdapter.onMovieClick(object :OnMovieClickListener{
            override fun onMovieClick(movie: Movie) {
                val intent = Intent(this@MainActivity , DetailActivity::class.java)
                intent.putExtra("movieId" ,movie.id.toLong())
                startActivity(intent)
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewTrendingMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    @SuppressLint("DiscouragedPrivateApi")
    private fun expandDefaultSize()
    {
        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null, 100 * 1024 * 1024) 
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}