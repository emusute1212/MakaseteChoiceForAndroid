package io.github.emusute1212.makasetechoice.usecase

import androidx.annotation.WorkerThread
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.data.repository.GroupDataRepository
import io.github.emusute1212.makasetechoice.ext.toMap
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroupUseCase @Inject constructor(
    private val repository: GroupDataRepository
) {
    @WorkerThread
    fun loadGroups() = repository.loadGroups().map {
        it.toMap()
    }

    @WorkerThread
    fun choiceGroup(members: List<Member>, splitNum: Int) {
        val groups = members
            .shuffled()
            .withIndex()
            .groupBy(
                {
                    "グループ${(it.index % splitNum) + 1}"
                }, {
                    it.value
                }
            )
        repository.saveGroups(groups)
    }
}