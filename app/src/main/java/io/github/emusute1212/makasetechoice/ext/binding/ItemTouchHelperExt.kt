package io.github.emusute1212.makasetechoice.ext.binding

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun ItemTouchHelper.Callback.bindRecyclerView(target: RecyclerView) {
    ItemTouchHelper(this).attachToRecyclerView(target)
}