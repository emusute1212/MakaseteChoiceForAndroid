package io.github.emusute1212.makasetechoice.ui.groups.grouping_member

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import io.github.emusute1212.makasetechoice.usecase.GroupUseCase
import kotlinx.coroutines.launch

class GroupingMemberViewModel @AssistedInject constructor(
    private val useCase: GroupUseCase,
    private val memberRepository: MemberDataRepository,
    @Assisted state: GroupingMemberUiState,
) : MavericksViewModel<GroupingMemberUiState>(state) {

    fun init() {
        viewModelScope.launch {
            memberRepository.loadMembers().collect {
                setState {
                    copy(
                        members = it,
                    )
                }
            }
        }
    }

    fun selectSplitNumber(number: Int) {
        setState {
            copy(
                currentSplitCount = number,
            )
        }
    }

    fun createGroup() {
        setState {
            copy(
                result = ChoiceResult.PROGRESS
            )
        }
        withState {
            if (it.currentSplitCount == 0) return@withState
            useCase.choiceGroup(
                members = it.members,
                splitNum = it.currentSplitCount,
            )
        }
        setState {
            copy(
                result = ChoiceResult.FINISH
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<GroupingMemberViewModel, GroupingMemberUiState> {
        override fun create(state: GroupingMemberUiState): GroupingMemberViewModel
    }

    companion object :
        MavericksViewModelFactory<GroupingMemberViewModel, GroupingMemberUiState> by hiltMavericksViewModelFactory()
}

@Module
@InstallIn(MavericksViewModelComponent::class)
interface GroupingMemberViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GroupingMemberViewModel::class)
    fun createGroupingMemberViewModellFactory(factory: GroupingMemberViewModel.Factory): AssistedViewModelFactory<*, *>
}
