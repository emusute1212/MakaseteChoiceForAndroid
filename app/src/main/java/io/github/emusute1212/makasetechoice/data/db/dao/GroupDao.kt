package io.github.emusute1212.makasetechoice.data.db.dao

import androidx.room.*
import io.github.emusute1212.makasetechoice.data.entity.Group
import io.github.emusute1212.makasetechoice.data.entity.GroupAndMember
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Transaction
    @Query("SELECT * FROM groups")
    fun loadGroups(): Flow<List<GroupAndMember>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGroups(groups: List<Group>)

    @Query("DELETE FROM groups")
    fun deleteAll()
}