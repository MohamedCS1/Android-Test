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
import com.example.androidtest.util.BitmapUtil
import com.example.androidtest.util.ConvertersUtil
import com.example.androidtest.util.DataSource
import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.detailMovie.Genre
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()
    var movieId:Long? = null
    var dataSource: DataSource = DataSource.Remote

    lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.extras?.getLong("movieId")


        movieId?.let { detailViewModel.getTrendingMovieDetail(BuildConfig.API_KEY , it) }

        lifecycleScope.launch {
            detailViewModel.trendingMovieDetail.collect{
                val moviePoster = async { BitmapUtil.getBitmap(this@DetailActivity,"https://image.tmdb.org/t/p/original/${it?.poster_path}" ?: "") }
                it?.let { detailMovieResponse -> linkDetailMovieWithView(detailMovieResponse ,dataSource) }
                it?.let { detailMovieResponse -> cacheDetailMovie(detailMovieResponse.copy(bitMapPoster = moviePoster.await())) }
            }
        }

        lifecycleScope.launch{
            detailViewModel.dataSource.collect{
                dataSource = it
            }
        }


        binding.buBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun linkDetailMovieWithView(detailMovieResponse: DetailMovieResponse ,dataSource: DataSource)
    {
        if (dataSource == DataSource.Remote) Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/original/${detailMovieResponse.poster_path}").into(binding.imageViewPoster)
        else Glide.with(this@DetailActivity).load(detailMovieResponse.bitMapPoster).into(binding.imageViewPoster)
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