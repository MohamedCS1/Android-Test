package com.example.androidtest.presentation.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidtest.R
import com.example.androidtest.databinding.CardMovieBinding
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import javax.inject.Inject

class MoviesAdapter:RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var trendingMoviesResponse:TrendingMoviesResponse = TrendingMoviesResponse()
    private lateinit var context: Context

    @Inject
    lateinit var onMovieClickListener: OnMovieClickListener
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
                binding.starsAverage.text = "$vote_average/10"
                binding.textViewReleaseDate.text = release_date
                binding.textViewPopularity.text = popularity.toString()

                Glide.with(context).load("https://image.tmdb.org/t/p/original/$poster_path")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(
                        R.drawable.ic_no_image
                    ).apply(
                    RequestOptions.bitmapTransform(RoundedCorners(25))
                ).into(binding.imageViewPoster)
            }
            holder.itemView.setOnClickListener {
                onMovieClickListener.onMovieClick(trendingMoviesResponse.results!![position])
            }
        }
    }

    inner class MovieViewHolder(val binding:CardMovieBinding):RecyclerView.ViewHolder(binding.root)

    fun submitTrendingMoviesResponse(trendingMoviesResponse: TrendingMoviesResponse)
    {
        this.trendingMoviesResponse = trendingMoviesResponse
        notifyDataSetChanged()
    }



    fun onMovieClick(onMovieClickListener: OnMovieClickListener)
    {
        this.onMovieClickListener = onMovieClickListener
    }
}