package com.example.androidtest.presentation.ui.main

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.database.CursorWindow
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.BuildConfig
import com.example.androidtest.databinding.ActivityMainBinding
import com.example.androidtest.presentation.ui.detail.DetailActivity
import com.example.androidtest.util.BitmapUtil.getBitmap
import com.example.domain.models.trendingMovies.Movie
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expandDefaultSize()

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

        moviesAdapter.onMovieClick(object :OnMovieClickListiner{
            override fun onMovieClick(itemView:View ,movie: Movie) {
                val intent = Intent(this@MainActivity , DetailActivity::class.java)
                val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, itemView, "transitionNameA" )
                intent.putExtra("movieId" ,movie.id.toLong())
                startActivity(intent ,options.toBundle())
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