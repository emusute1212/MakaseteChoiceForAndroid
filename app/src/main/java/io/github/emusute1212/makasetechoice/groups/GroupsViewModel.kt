package io.github.emusute1212.makasetechoice.groups

import androidx.lifecycle.*
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.usecase.GroupUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
    private val useCase: GroupUseCase
) : ViewModel() {
    val groups: LiveData<Map<String, List<Member>>> by lazy {
        useCase.loadGroups().asLiveData(Dispatchers.IO)
    }
    val isEmptyGroups = MediatorLiveData<Boolean>().also {
        it.addSource(groups) { groups ->
            it.value = groups.isNullOrEmpty()
        }
    }
    val splitNum = MutableLiveData(1)

    fun choiceGroup(member: List<Member>) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.choiceGroup(member, checkNotNull(splitNum.value))
        }
    }
}
