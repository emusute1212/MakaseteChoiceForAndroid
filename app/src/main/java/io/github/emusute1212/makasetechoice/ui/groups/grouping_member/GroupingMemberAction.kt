package io.github.emusute1212.makasetechoice.ui.groups.grouping_member

fun interface GroupingMemberAction {
    operator fun invoke(action: GroupingMemberUiAction)
}

sealed interface GroupingMemberUiAction {
    data class SelectSplitNumber(
        val splitNumber: Int,
    ): GroupingMemberUiAction

    data object DoChoice : GroupingMemberUiAction
    data object Close: GroupingMemberUiAction
}