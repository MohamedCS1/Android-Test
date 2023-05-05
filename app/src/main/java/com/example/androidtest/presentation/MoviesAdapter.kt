package com.example.androidtest.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtest.BuildConfig
import com.example.androidtest.databinding.CardMovieBinding
import com.example.domain.models.trendingMovies.TrendingMoviesResponse

class MoviesAdapter:RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var trendingMoviesResponse:TrendingMoviesResponse = TrendingMoviesResponse(null ,null ,null ,null)
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        return MovieViewHolder(CardMovieBinding.inflate(LayoutInflater.from(parent.context) ,parent ,false))
    }

    override fun getItemCount(): Int {
        return trendingMoviesResponse.results?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder)
        {
            with(trendingMoviesResponse.results!![position])
            {
                binding.textViewTitle.text = title
                binding.starsAverage.text = vote_average.toString()
                binding.textViewReleaseDate.text = release_date
                binding.textViewPopularity.text = popularity.toString()
                Glide.with(context).load("https://image.tmdb.org/t/p/original/$poster_path").into(binding.imageViewPoster)
            }
        }
    }

    inner class MovieViewHolder(val binding:CardMovieBinding):RecyclerView.ViewHolder(binding.root)

    fun submitTrendingMoviesResponse(trendingMoviesResponse: TrendingMoviesResponse)
    {
        this.trendingMoviesResponse = trendingMoviesResponse
        notifyDataSetChanged()
    }

}