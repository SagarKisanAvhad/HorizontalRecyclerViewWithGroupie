package com.delaroystudios.snaprecyclerview.item

import com.bumptech.glide.Glide
import com.delaroystudios.snaprecyclerview.R
import com.delaroystudios.snaprecyclerview.model.Movie
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.movie_card.view.thumbnail
import kotlinx.android.synthetic.main.movie_card.view.title
import kotlinx.android.synthetic.main.movie_card.view.userrating

class MovieItem(
  private val movie: Movie
) : Item<ViewHolder>() {

  override fun getLayout(): Int = R.layout.movie_card

  override fun bind(
    viewHolder: ViewHolder,
    position: Int
  ) {
    val context = viewHolder.root.context

    viewHolder.itemView.title.setText(movie.getOriginalTitle())
    val vote = movie.getVoteAverage()
    viewHolder.itemView.userrating.setText(vote.toString())

    val poster = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath()

    Glide.with(context)
        .load(poster)
        .placeholder(R.drawable.load)
        .into(viewHolder.itemView.thumbnail)
  }


}