package io.github.emusute1212.makasetechoice.members.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import javax.inject.Inject

class AddMemberViewModel @Inject constructor(
    private val repository: MemberDataRepository
) : ViewModel() {
    val newMemberName = MutableLiveData<String>("")

    fun onAddButtonClick() {
        repository.addMember(checkNotNull(newMemberName.value))
    }
}