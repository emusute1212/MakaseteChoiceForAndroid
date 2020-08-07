package io.github.emusute1212.makasetechoice.ext.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.members.MemberItem

@BindingAdapter("initMembers")
fun RecyclerView.initMembers(list: List<Member>) {
    adapter = GroupAdapter<GroupieViewHolder>().also { adapter ->
        list.forEach { item ->
            adapter.add(MemberItem(item))
        }
    }
}