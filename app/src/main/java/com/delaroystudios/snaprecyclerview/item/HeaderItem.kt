package com.delaroystudios.snaprecyclerview.item

import androidx.annotation.StringRes
import com.delaroystudios.snaprecyclerview.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_header.view.tv_title

class HeaderItem(
  @StringRes private val header: Int
) : Item<ViewHolder>() {

  override fun getLayout() = R.layout.item_header

  override fun bind(
    viewHolder: ViewHolder,
    position: Int
  ) {
    viewHolder.itemView.tv_title.setText(header)
  }

}