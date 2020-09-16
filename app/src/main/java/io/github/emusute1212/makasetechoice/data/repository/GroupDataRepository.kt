package io.github.emusute1212.makasetechoice.data.repository

import androidx.annotation.WorkerThread
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.db.dao.GroupDao
import io.github.emusute1212.makasetechoice.data.entity.GroupAndMember
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.ext.binding.toGroups
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroupDataRepository @Inject constructor(
    private val database: MakaseteChoiceDatabase
) {
    private val dao: GroupDao
        get() = database.groupDao()

    @WorkerThread
    fun loadGroups(): Flow<List<GroupAndMember>> {
        return dao.loadGroups()
    }

    @WorkerThread
    fun saveGroups(groups: Map<String, List<Member>>) {
        dao.deleteAll()
        dao.saveGroups(groups.toGroups())
    }
}