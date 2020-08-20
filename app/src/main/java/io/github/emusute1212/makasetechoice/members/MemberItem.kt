package io.github.emusute1212.makasetechoice.members

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.github.emusute1212.makasetechoice.R
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.databinding.MemberItemViewBinding


data class MemberItem(
    private val member: Member,
    private val onDeleteMember: OnDeleteMember? = null
) : BindableItem<MemberItemViewBinding>(
    member.id.toLong()
) {
    override fun getLayout(): Int = R.layout.member_item_view

    override fun bind(viewBinding: MemberItemViewBinding, position: Int) {
        viewBinding.member = member
        viewBinding.deletable = onDeleteMember != null
        viewBinding.onDeleteMember = onDeleteMember
    }

    override fun initializeViewBinding(view: View): MemberItemViewBinding {
        return MemberItemViewBinding.bind(view)
    }
}