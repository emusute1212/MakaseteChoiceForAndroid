package io.github.emusute1212.makasetechoice.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.emusute1212.makasetechoice.data.entity.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MembersDao {
    @Query("SELECT * FROM members")
    fun loadMembers(): Flow<List<Member>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMember(name: Member)
}