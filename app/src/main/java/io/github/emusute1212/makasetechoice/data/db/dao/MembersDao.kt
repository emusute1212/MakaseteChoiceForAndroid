package io.github.emusute1212.makasetechoice.data.db.dao

import androidx.annotation.WorkerThread
import androidx.room.*
import io.github.emusute1212.makasetechoice.data.entity.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MembersDao {
    @WorkerThread
    @Query("SELECT * FROM members")
    fun loadMembers(): Flow<List<Member>>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMember(name: Member)

    @WorkerThread
    @Delete
    fun deleteMember(member: Member)
}