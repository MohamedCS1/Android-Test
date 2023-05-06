package com.example.androidtest.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.androidtest.BuildConfig
import com.example.androidtest.databinding.ActivityDetailBinding
import com.example.androidtest.util.ConvertersUtil
import com.example.domain.models.detailMovie.DetailMovieResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()
    var movieId:Long? = null

    lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.extras?.getLong("movieId")


        movieId?.let { detailViewModel.getTrendingMovieDetail(BuildConfig.API_KEY , it) }

        lifecycleScope.launch {
            detailViewModel.trendingMovieDetail.collect{
                it?.let { detailMovieResponse -> linkDetailMovieWithView(detailMovieResponse) }
                it?.let { detailMovieResponse -> cacheDetailMovie(detailMovieResponse) }
            }
        }



        binding.buBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun linkDetailMovieWithView(detailMovieResponse: DetailMovieResponse)
    {

        Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/original/${detailMovieResponse.poster_path}").diskCacheStrategy(
            DiskCacheStrategy.ALL).into(binding.imageViewPoster)
        binding.textViewTitle.text = detailMovieResponse.title
        binding.textViewCategory.text = detailMovieResponse.genres?.let { arrayGenres -> ConvertersUtil.turnGenresToCategoriesString(arrayGenres) }
        binding.textViewOverview.text = detailMovieResponse.overview
        binding.textViewStatus.text = detailMovieResponse.status
        binding.textViewOriginalLanguage.text = detailMovieResponse.original_language
        binding.textViewPopularity.text = detailMovieResponse.popularity.toString()
        binding.starsAverage.text = "${detailMovieResponse.vote_average}/10"
    }

    fun cacheDetailMovie(detailMovieResponse: DetailMovieResponse)
    {
        detailMovieResponse.let { movie -> detailViewModel.insertDetailTrendingMovie(movie) }
    }
}