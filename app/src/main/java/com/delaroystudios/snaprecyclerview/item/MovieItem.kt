package com.delaroystudios.snaprecyclerview.item

import com.delaroystudios.snaprecyclerview.model.Movie

class MovieItem(
  private val movie: Movie
) {

  override fun equals(other: Any?): Boolean {
    return super.equals(other)
  }

  override fun hashCode(): Int {
    return super.hashCode()
  }

  override fun toString(): String {
    return super.toString()
  }
}