package com.c4pn91.opchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c4pn91.opchallenge.R
import com.c4pn91.opchallenge.domain.model.Movie
import com.c4pn91.opchallenge.util.Constants

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val overviewTextView: TextView = itemView.findViewById(R.id.overviewTextView)
        val releaseDateTextView: TextView = itemView.findViewById(R.id.releaseDateTextView)
        val popularityTextView: TextView = itemView.findViewById(R.id.popularityTextView)
        val voteAverageTextView: TextView = itemView.findViewById(R.id.voteAverageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title ?: "No title"
        holder.overviewTextView.text = movie.overview ?: "No overview"
        holder.releaseDateTextView.text = movie.releaseDate ?: "No release date"
        holder.popularityTextView.text = ("Popularity: " + movie.popularity?.toString()) ?: "No popularity"
        holder.voteAverageTextView.text = ("Vote average: " + movie.voteAverage?.toString()) ?: "No average"

        Glide.with(holder.itemView.context)
            .load(Constants.BASE_IMGS_SMALL + movie.posterPath)
            .into(holder.posterImageView)
    }

    override fun getItemCount() = movies.size
}