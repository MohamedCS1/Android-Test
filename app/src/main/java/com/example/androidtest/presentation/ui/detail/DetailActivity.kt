package com.example.androidtest.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.androidtest.BuildConfig
import com.example.androidtest.R
import com.example.androidtest.databinding.ActivityDetailBinding
import com.example.androidtest.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()
    var movieId:Long? = null

    lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.extras?.getLong("movieId")

        Toast.makeText(baseContext ,movieId.toString() ,Toast.LENGTH_SHORT).show()


        movieId?.let { detailViewModel.getTrendingMovieDetail(BuildConfig.API_KEY , it) }

        lifecycleScope.launch {
            detailViewModel.trendingMovieDetail.collect{
                Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/original/${it?.poster_path}").into(binding.imageViewPoster)
                binding.textViewTitle.text = it?.title
                binding.textViewCategory.text = it?.toString()
                binding.textViewOverview.text = it?.overview
                binding.textViewStatus.text = it?.status
                it?.let { it1 -> detailViewModel.insertDetailTrendingMovie(it1) }
            }
        }

        binding.buBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}