package io.github.emusute1212.makasetechoice.groups

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.databinding.GroupItemViewBinding


data class GroupItem(
    private val groupName: String
) : BindableItem<GroupItemViewBinding>(
    groupName.hashCode().toLong()
) {
    override fun getLayout(): Int = R.layout.group_item_view

    override fun bind(viewBinding: GroupItemViewBinding, position: Int) {
        viewBinding.groupName = groupName
    }

    override fun initializeViewBinding(view: View): GroupItemViewBinding {
        return GroupItemViewBinding.bind(view)
    }
}