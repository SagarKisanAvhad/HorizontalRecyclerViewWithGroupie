package com.delaroystudios.snaprecyclerview.item

import com.bumptech.glide.Glide
import com.delaroystudios.snaprecyclerview.R
import com.delaroystudios.snaprecyclerview.model.Movie
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class MovieItem(
  private val movie: Movie
) : Item<ViewHolder>() {

  override fun getLayout(): Int = R.layout.movie_card

  override fun bind(
    viewHolder: ViewHolder,
    position: Int
  ) {
    val context = viewHolder.root.context
    viewHolder.title.setText(movie.getOriginalTitle())
    val vote = movie.getVoteAverage()
    viewHolder.userrating.setText(vote)

    val poster = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath()

    Glide.with(context)
        .load(poster)
        .placeholder(R.drawable.load)
        .into(viewHolder.thumbnail)
  }


}