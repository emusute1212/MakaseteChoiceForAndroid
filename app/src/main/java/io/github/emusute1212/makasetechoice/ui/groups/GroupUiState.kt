package io.github.emusute1212.makasetechoice.ui.groups

import com.airbnb.mvrx.MavericksState
import io.github.emusute1212.makasetechoice.data.entity.Member

data class GroupUiState(
    val groups: Map<String, List<Member>> = emptyMap(),
) : MavericksState
