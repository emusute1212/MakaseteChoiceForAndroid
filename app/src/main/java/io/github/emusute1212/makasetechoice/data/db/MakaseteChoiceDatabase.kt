package io.github.emusute1212.makasetechoice.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.emusute1212.makasetechoice.data.db.dao.GroupDao
import io.github.emusute1212.makasetechoice.data.db.dao.MembersDao
import io.github.emusute1212.makasetechoice.data.entity.Group
import io.github.emusute1212.makasetechoice.data.entity.Member

@Database(
    entities = [
        Member::class,
        Group::class
    ], version = 1
)
abstract class MakaseteChoiceDatabase : RoomDatabase() {
    abstract fun memberDao(): MembersDao
    abstract fun groupDao(): GroupDao
}