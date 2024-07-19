package io.github.emusute1212.makasetechoice.ui.members

import com.airbnb.mvrx.MavericksState
import io.github.emusute1212.makasetechoice.data.entity.Member

data class MemberListUiState(
    val members: List<Member> = emptyList(),
) : MavericksState
