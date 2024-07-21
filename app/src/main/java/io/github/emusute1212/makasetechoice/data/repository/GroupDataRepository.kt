package io.github.emusute1212.makasetechoice.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.db.dao.GroupDao
import io.github.emusute1212.makasetechoice.data.entity.GroupAndMember
import io.github.emusute1212.makasetechoice.data.entity.Member
import io.github.emusute1212.makasetechoice.ext.toGroups
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface GroupDataRepository {
    @WorkerThread
    fun loadGroups(): Flow<List<GroupAndMember>>

    @WorkerThread
    fun saveGroups(groups: Map<String, List<Member>>)
}

@VisibleForTesting
class GroupDataRepositoryImpl(
    private val database: MakaseteChoiceDatabase
) : GroupDataRepository {
    private val dao: GroupDao
        get() = database.groupDao()

    @WorkerThread
    override fun loadGroups(): Flow<List<GroupAndMember>> {
        return dao.loadGroups()
    }

    @WorkerThread
    override fun saveGroups(groups: Map<String, List<Member>>) {
        dao.deleteAll()
        dao.saveGroups(groups.toGroups())
    }
}

@Module
@InstallIn(SingletonComponent::class)
class GroupDataRepositoryModule {
    @Provides
    @Singleton
    fun provideGroupDataRepository(
        database: MakaseteChoiceDatabase
    ): GroupDataRepository {
        return GroupDataRepositoryImpl(
            database = database,
        )
    }
}
