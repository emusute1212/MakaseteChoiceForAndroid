package io.github.emusute1212.makasetechoice.ui.members

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemberListViewModel @AssistedInject constructor(
    private val repository: MemberDataRepository,
    @Assisted state: MemberListUiState,
) : MavericksViewModel<MemberListUiState>(state) {

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadMembers().collect {
                setState {
                    copy(
                        members = it,
                    )
                }
            }
        }
    }

    fun deleteMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMember(
                member = member
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MemberListViewModel, MemberListUiState> {
        override fun create(state: MemberListUiState): MemberListViewModel
    }

    companion object :
        MavericksViewModelFactory<MemberListViewModel, MemberListUiState> by hiltMavericksViewModelFactory()
}

@Module
@InstallIn(MavericksViewModelComponent::class)
interface MemberListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MemberListViewModel::class)
    fun createMemberListViewModelFactory(factory: MemberListViewModel.Factory): AssistedViewModelFactory<*, *>
}
