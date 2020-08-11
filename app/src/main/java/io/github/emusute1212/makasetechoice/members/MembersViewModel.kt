package io.github.emusute1212.makasetechoice.members

import androidx.lifecycle.*
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    private val repository: MemberDataRepository
) : ViewModel() {
    private val _members = MutableLiveData<List<Member>>(emptyList())
    val members: LiveData<List<Member>>
        get() = _members
    val isEmptyMember = MediatorLiveData<Boolean>().also {
        it.addSource(members) { members ->
            it.value = members.isEmpty()
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _members.postValue(repository.loadMembers())
        }
    }
}