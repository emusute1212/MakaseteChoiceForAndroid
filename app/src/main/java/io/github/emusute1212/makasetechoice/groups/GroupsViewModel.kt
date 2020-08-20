package io.github.emusute1212.makasetechoice.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.emusute1212.makasetechoice.data.entity.Member

class GroupsViewModel : ViewModel() {
    private val _groups = MutableLiveData<Map<String, List<Member>>>()
    val groups: LiveData<Map<String, List<Member>>>
        get() = _groups
    val isEmptyGroups = MediatorLiveData<Boolean>().also {
        it.addSource(groups) { groups ->
            it.value = groups.isEmpty()
        }
    }
}
