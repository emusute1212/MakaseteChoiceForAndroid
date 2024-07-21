package io.github.emusute1212.makasetechoice.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.db.dao.MembersDao
import io.github.emusute1212.makasetechoice.data.entity.Member
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface MemberDataRepository {
    @WorkerThread
    fun loadMembers(): Flow<List<Member>>

    @WorkerThread
    fun addMember(name: String)

    @WorkerThread
    fun deleteMember(member: Member)
}

@VisibleForTesting
class MemberDataRepositoryImpl(
    private val database: MakaseteChoiceDatabase
) : MemberDataRepository {
    private val dao: MembersDao
        get() = database.memberDao()

    @WorkerThread
    override fun loadMembers(): Flow<List<Member>> {
        return dao.loadMembers()
    }

    @WorkerThread
    override fun addMember(name: String) {
        dao.insertMember(Member(0, name))
    }

    @WorkerThread
    override fun deleteMember(member: Member) {
        dao.deleteMember(member)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class MemberDataRepositoryModule {
    @Provides
    @Singleton
    fun provideMemberDataRepository(
        database: MakaseteChoiceDatabase
    ): MemberDataRepository {
        return MemberDataRepositoryImpl(
            database = database,
        )
    }
}
