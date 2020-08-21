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
    val memberChoiceNumberList = MediatorLiveData<List<Int>>().also {
        it.addSource(members) { members ->
            it.value = members.mapIndexed { index, _ -> index }
        }
    }
    val isEmptyMember = MediatorLiveData<Boolean>().also {
        it.addSource(members) { members ->
            it.value = members.isEmpty()
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            _members.postValue(repository.loadMembers())
        }
    }
}