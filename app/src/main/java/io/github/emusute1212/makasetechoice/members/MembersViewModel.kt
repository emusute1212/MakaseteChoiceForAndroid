package io.github.emusute1212.makasetechoice.members

import androidx.lifecycle.*
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    private val repository: MemberDataRepository
) : ViewModel() {
    private val _members = MutableLiveData<List<Member>>()
    val members: LiveData<List<Member>>
        get() = _members
    val memberChoiceNumberList = _members.map {
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

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            _members.postValue(repository.loadMembers())
//            _members.postValue(emptyList())
        }
    }

    fun onAddButtonClick() {
        repository.addMember(checkNotNull(newMemberName.value))
    }
}