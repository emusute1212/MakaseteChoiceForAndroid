package io.github.emusute1212.makasetechoice.members

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    private val repository: MemberDataRepository
) : ViewModel() {
    val members: StateFlow<List<Member>> by lazy {
        repository.loadMembers().stateIn(viewModelScope, Lazily, emptyList())
    }
    val memberChoiceNumberList: StateFlow<List<Int>> by lazy {
        members.map {
            it.mapIndexed { index, _ ->
                index + 1
            }
        }.stateIn(viewModelScope, Lazily, emptyList())
    }
    val newMemberName = MutableStateFlow("")
    val isEmptyMember: StateFlow<Boolean> by lazy {
        members.map {
            it.isNullOrEmpty()
        }.stateIn(viewModelScope, Lazily, false)
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
                resetMemberAddText()
            }
        }
    }

    fun resetMemberAddText() {
        newMemberName.value = ""
    }

    fun onDeleteMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMember(member)
        }
    }
}