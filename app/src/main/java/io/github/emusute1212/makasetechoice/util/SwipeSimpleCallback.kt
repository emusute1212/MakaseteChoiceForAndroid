package io.github.emusute1212.makasetechoice.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeSimpleCallback(
    swipeDirs: Int
) : ItemTouchHelper.SimpleCallback(
    0,
    swipeDirs
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}