package io.github.emusute1212.makasetechoice.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.emusute1212.makasetechoice.data.db.dao.GroupDao
import io.github.emusute1212.makasetechoice.data.db.dao.MembersDao
import io.github.emusute1212.makasetechoice.data.entity.Group
import io.github.emusute1212.makasetechoice.data.entity.Member
import javax.inject.Singleton

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

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MakaseteChoiceDatabase {
        return Room.databaseBuilder(
            context,
            MakaseteChoiceDatabase::class.java,
            "makasete_choice_database.db"
        ).fallbackToDestructiveMigration().build()
    }
}