package io.github.emusute1212.makasetechoice.ext.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.groups.GroupItem
import io.github.emusute1212.makasetechoice.members.MemberItem

@BindingAdapter("bindMembers")
fun RecyclerView.bindMembers(list: List<Member>?) {
    if (list == null) return
    if (adapter == null) {
        GroupAdapter<GroupieViewHolder>().also { groupieAdapter ->
            adapter = groupieAdapter
            groupieAdapter.update(list.map {
                MemberItem(it)
            })
        }
    } else {
        (adapter as GroupAdapter).update(list.map {
            MemberItem(it)
        })
    }
}

@BindingAdapter("bindGroups")
fun RecyclerView.bindGroups(groups: Map<String, List<Member>>?) {
    if (groups == null) return
    val sections = groups.flatMap { item ->
        Section().also { section ->
            section.setHeader(GroupItem(item.key))
            section.update(item.value.map {
                MemberItem(it)
            })
        }.let {
            listOf(it)
        }
    }
    if (adapter == null) {
        GroupAdapter<GroupieViewHolder>().also { groupieAdapter ->
            adapter = groupieAdapter
            groupieAdapter.update(sections)
        }
    } else {
        (adapter as GroupAdapter).update(sections)
    }
}