package io.github.emusute1212.makasetechoice.ui.groups

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
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import io.github.emusute1212.makasetechoice.usecase.GroupUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupViewModel @AssistedInject constructor(
    private val useCase: GroupUseCase,
    private val memberRepository: MemberDataRepository,
    @Assisted state: GroupUiState,
) : MavericksViewModel<GroupUiState>(state) {

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.loadGroups().collect {
                setState {
                    copy(
                        groups = it,
                    )
                }
            }
        }
    }

    fun deleteMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            memberRepository.deleteMember(
                member = member,
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<GroupViewModel, GroupUiState> {
        override fun create(state: GroupUiState): GroupViewModel
    }

    companion object :
        MavericksViewModelFactory<GroupViewModel, GroupUiState> by hiltMavericksViewModelFactory()
}

@Module
@InstallIn(MavericksViewModelComponent::class)
interface GroupViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GroupViewModel::class)
    fun createGroupViewModelFactory(factory: GroupViewModel.Factory): AssistedViewModelFactory<*, *>
}
