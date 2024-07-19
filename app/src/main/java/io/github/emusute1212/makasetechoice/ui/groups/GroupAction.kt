package io.github.emusute1212.makasetechoice.ui.groups

import io.github.emusute1212.makasetechoice.data.entity.Member

fun interface GroupAction {
    operator fun invoke(action: GroupUiAction)
}

sealed interface GroupUiAction {
    data object ClickGroupMember : GroupUiAction
    data class DeleteMember(
        val member: Member,
    ) : GroupUiAction
}
