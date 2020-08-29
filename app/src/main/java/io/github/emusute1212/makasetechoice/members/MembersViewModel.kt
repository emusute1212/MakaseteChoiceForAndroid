package io.github.emusute1212.makasetechoice.members

import androidx.lifecycle.*
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    // DataBindingだとなぜかこの形式でしか使えなかった。
    // OnDeleteMemberをJavaで書けば行けるかもしれないが、コスト的にこっちでも問題ないので、このまま進める。
    val onDeleteMember = object : OnDeleteMember {
        override fun delete(member: Member) {
            onDeleteMember(member)
        }
    }

    fun onAddButtonClick() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMember(checkNotNull(newMemberName.value))
            withContext(Dispatchers.Main) {
                // 入力値のリセット
                newMemberName.value = ""
            }
        }
    }

    fun onDeleteMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMember(member)
        }
    }
}