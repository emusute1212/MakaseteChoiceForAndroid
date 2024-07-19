package io.github.emusute1212.makasetechoice.ui.groups.grouping_member

import com.airbnb.mvrx.MavericksState
import io.github.emusute1212.makasetechoice.data.entity.Member

data class GroupingMemberUiState(
    val members: List<Member> = emptyList(),
    val currentSplitCount: Int = 0,
    val result: ChoiceResult = ChoiceResult.NOT_STARTED,
) : MavericksState

enum class ChoiceResult {
    NOT_STARTED,
    PROGRESS,
    FINISH,
    ERROR,
}
