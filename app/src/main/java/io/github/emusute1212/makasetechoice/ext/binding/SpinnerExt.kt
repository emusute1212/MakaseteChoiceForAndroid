package io.github.emusute1212.makasetechoice.ext.binding

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter(value = ["selectedItemPositionSafety", "attrChange"], requireAll = false)
fun Spinner.setPosition(position: Int, attrChange: InverseBindingListener) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            if (p2 >= adapter.count) return
            attrChange.onChange()
        }
    }

    if (position >= adapter.count) return
    setSelection(position)
}

@InverseBindingAdapter(attribute = "selectedItemPositionSafety", event = "attrChange")
fun Spinner.getPosition(): Int {
    return selectedItemPosition
}