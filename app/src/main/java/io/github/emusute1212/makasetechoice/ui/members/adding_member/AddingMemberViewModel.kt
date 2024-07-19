package io.github.emusute1212.makasetechoice.ui.members.adding_member

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

class AddingMemberViewModel @AssistedInject constructor(
    private val repository: MemberDataRepository,
    @Assisted state: AddingMemberUiState,
) : MavericksViewModel<AddingMemberUiState>(state) {

    fun inputMember(currentValue: String) {
        setState {
            copy(
                memberName = currentValue,
            )
        }
    }

    fun createMember() {
        setState {
            copy(
                result = AddingResult.PROGRESS,
            )
        }
        withState {
            repository.addMember(name = it.memberName)
        }
        setState {
            copy(
                result = AddingResult.FINISH,
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<AddingMemberViewModel, AddingMemberUiState> {
        override fun create(state: AddingMemberUiState): AddingMemberViewModel
    }

    companion object :
        MavericksViewModelFactory<AddingMemberViewModel, AddingMemberUiState> by hiltMavericksViewModelFactory()
}

@Module
@InstallIn(MavericksViewModelComponent::class)
interface AddingMemberViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddingMemberViewModel::class)
    fun createAddingMemberViewModellFactory(factory: AddingMemberViewModel.Factory): AssistedViewModelFactory<*, *>
}
