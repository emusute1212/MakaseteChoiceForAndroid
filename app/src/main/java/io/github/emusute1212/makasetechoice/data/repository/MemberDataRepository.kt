package io.github.emusute1212.makasetechoice.data.repository

import androidx.annotation.WorkerThread
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.db.dao.MembersDao
import io.github.emusute1212.makasetechoice.data.entity.Member
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberDataRepository @Inject constructor(
    private val database: MakaseteChoiceDatabase
) {
    private val dao: MembersDao
        get() = database.memberDao()

    @WorkerThread
    fun loadMembers(): Flow<List<Member>> {
        return dao.loadMembers()
    }

    @WorkerThread
    fun addMember(name: String) {
        dao.insertMember(Member(0, name))
    }

    @WorkerThread
    fun deleteMember(member: Member) {
        dao.deleteMember(member)
    }
}