package io.github.emusute1212.makasetechoice.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.emusute1212.makasetechoice.data.db.dao.MembersDao
import io.github.emusute1212.makasetechoice.data.entity.Member

@Database(entities = [Member::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MembersDao
}