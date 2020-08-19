package io.github.emusute1212.makasetechoice.groups

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.databinding.MemberItemViewBinding


class GroupItem(
) : BindableItem<MemberItemViewBinding>() {
    override fun getLayout(): Int = R.layout.member_item_view

    override fun bind(viewBinding: MemberItemViewBinding, position: Int) {
//        viewBinding.group = group
    }

    override fun initializeViewBinding(view: View): MemberItemViewBinding {
        return MemberItemViewBinding.bind(view)
    }
}