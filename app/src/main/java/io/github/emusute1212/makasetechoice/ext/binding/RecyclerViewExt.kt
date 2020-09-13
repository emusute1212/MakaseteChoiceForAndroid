package io.github.emusute1212.makasetechoice.ext.binding

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.groups.GroupItem
import io.github.emusute1212.makasetechoice.members.MemberItem
import io.github.emusute1212.makasetechoice.members.OnDeleteMember
import io.github.emusute1212.makasetechoice.settings.SettingItem
import io.github.emusute1212.makasetechoice.settings.SettingMenuItems
import io.github.emusute1212.makasetechoice.settings.SettingsViewModel
import io.github.emusute1212.makasetechoice.util.SwipeSimpleCallback

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
    // スワイプでも削除できるようにする。
    object : SwipeSimpleCallback(ItemTouchHelper.LEFT) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (viewHolder as GroupieViewHolder).also { groupieViewHolder ->
                (groupieViewHolder.item as MemberItem).also { memberItem ->
                    onDeleteMember.delete(memberItem.member)
                }
            }
        }

        // スワイプ時に背景を赤色にする（別のやり方がありそうだか、今はこれで進める）
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            viewHolder.itemView.also {
                ColorDrawable(
                    resources.getColor(
                        R.color.delete_background,
                        null
                    )
                ).also { background ->
                    background.setBounds(
                        it.right + dX.toInt(),
                        it.top,
                        it.right,
                        it.bottom
                    )
                    background.draw(c)
                }
            }
        }
    }.bindRecyclerView(this)
}

@BindingAdapter(value = ["bindSettings", "viewModel"], requireAll = true)
fun RecyclerView.bindSettings(items: List<SettingMenuItems>?, viewModel: SettingsViewModel) {
    if (items == null) return
    if (adapter == null) {
        GroupAdapter<GroupieViewHolder>().also { groupieAdapter ->
            adapter = groupieAdapter
            groupieAdapter.update(items.map {
                SettingItem(it, viewModel)
            })
        }
    } else {
        (adapter as GroupAdapter).update(items.map {
            SettingItem(it, viewModel)
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