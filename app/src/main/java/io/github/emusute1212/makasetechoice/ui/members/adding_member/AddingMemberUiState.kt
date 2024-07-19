package io.github.emusute1212.makasetechoice.ui.members.adding_member

import com.airbnb.mvrx.MavericksState

data class AddingMemberUiState(
    val memberName: String = "",
    val result: AddingResult = AddingResult.NOT_STARTED,
) : MavericksState

enum class AddingResult {
    NOT_STARTED,
    PROGRESS,
    FINISH,
    ERROR,
}
