package io.github.emusute1212.makasetechoice.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.emusute1212.makasetechoice.data.entity.Member

class MembersViewModel : ViewModel() {
    //    private val _members = MutableLiveData<List<Member>>(emptyList())
    private val _members =
        MutableLiveData<List<Member>>(listOf(Member(1, "aiueo"), Member(2, "kakikukeko")))
    val members: LiveData<List<Member>>
        get() = _members
    val isEmptyMember = MediatorLiveData<Boolean>().also {
        it.addSource(members) { members ->
            it.value = members.isEmpty()
        }
    }
}