package io.github.emusute1212.makasetechoice.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.usecase.GroupUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
    private val useCase: GroupUseCase
) : ViewModel() {
    val groups: StateFlow<Map<String, List<Member>>> by lazy {
        useCase.loadGroups().stateIn(viewModelScope, Lazily, emptyMap())
    }
    val isEmptyGroups: StateFlow<Boolean> by lazy {
        groups.map {
            it.isNullOrEmpty()
        }.stateIn(viewModelScope, Lazily, false)
    }

    /**
     * positionで入っているので、使う場合は+1をする
     */
    val splitNumPosition = MutableStateFlow(0)

    fun choiceGroup(member: List<Member>) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.choiceGroup(member, checkNotNull(splitNumPosition.value) + 1)
        }
    }
}
