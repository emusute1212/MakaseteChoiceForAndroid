package io.github.emusute1212.makasetechoice.ext.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.groups.GroupItem
import io.github.emusute1212.makasetechoice.members.MemberItem
import io.github.emusute1212.makasetechoice.members.OnDeleteMember

@BindingAdapter(value = ["bindMembers", "onDeleteMember"], requireAll = true)
fun RecyclerView.bindMembers(list: List<Member>?, onDeleteMember: OnDeleteMember) {
    if (list == null) return
    if (adapter == null) {
        GroupAdapter<GroupieViewHolder>().also { groupieAdapter ->
            adapter = groupieAdapter
            groupieAdapter.update(list.map {
                MemberItem(it, onDeleteMember)
            })
        }
    } else {
        (adapter as GroupAdapter).update(list.map {
            MemberItem(it, onDeleteMember)
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