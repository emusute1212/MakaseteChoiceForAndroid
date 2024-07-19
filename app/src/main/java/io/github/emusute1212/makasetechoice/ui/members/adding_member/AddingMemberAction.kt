package io.github.emusute1212.makasetechoice.ui.members.adding_member

fun interface AddingMemberAction {
    operator fun invoke(action: AddingMemberUiAction)
}

sealed interface AddingMemberUiAction {
    data object AddMember : AddingMemberUiAction
    data class InputMember(
        val currentValue: String,
    ) : AddingMemberUiAction

    data object Close : AddingMemberUiAction
}
