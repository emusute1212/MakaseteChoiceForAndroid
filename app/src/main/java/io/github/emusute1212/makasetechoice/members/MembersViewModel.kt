package io.github.emusute1212.makasetechoice.members

import androidx.lifecycle.*
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    private val repository: MemberDataRepository
) : ViewModel() {
    val members by lazy {
        repository.loadMembers().asLiveData(Dispatchers.IO)
    }
    val memberChoiceNumberList = members.map {
        it.mapIndexed { index, _ ->
            index
        }
    }
    val newMemberName = MutableLiveData<String>("")
    val isEmptyMember = MediatorLiveData<Boolean>().also {
        it.addSource(members) { members ->
            it.value = members.isNullOrEmpty()
        }
    }

    fun onAddButtonClick() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMember(checkNotNull(newMemberName.value))
        }
        // 入力値のリセット
        newMemberName.value = ""
    }
}