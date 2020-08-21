package io.github.emusute1212.makasetechoice.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.usecase.GroupUseCase
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
    private val useCase: GroupUseCase
) : ViewModel() {
    private val _groups = MutableLiveData<Map<String, List<Member>>>()
    val groups: LiveData<Map<String, List<Member>>>
        get() = _groups
    val isEmptyGroups = MediatorLiveData<Boolean>().also {
        it.addSource(groups) { groups ->
            it.value = groups.isEmpty()
        }
    }
    val splitNum = MutableLiveData(1)

    fun init() {
        // TODO This is mock
        _groups.value = mapOf(
            "グループ1" to listOf(
                Member(1, "aaaaa"),
                Member(2, "bbbb")
            ),
            "グループ2" to listOf(
                Member(3, "cccc"),
                Member(4, "dddd")
            )
        )
    }

    fun choiceGroup(member: List<Member>) {
        _groups.value = useCase.choiceGroup(member, checkNotNull(splitNum.value))
    }
}
