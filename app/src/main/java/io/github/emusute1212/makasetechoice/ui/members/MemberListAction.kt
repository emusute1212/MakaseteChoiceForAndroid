package io.github.emusute1212.makasetechoice.ui.members

import io.github.emusute1212.makasetechoice.data.entity.Member

fun interface MemberListAction {
    operator fun invoke(action: MemberListUiAction)
}

sealed interface MemberListUiAction {
    data object ClickAddMember : MemberListUiAction
    data class ClickDeleteMember(
        val member: Member,
    ) : MemberListUiAction
}
