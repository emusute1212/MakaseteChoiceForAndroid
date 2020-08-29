package io.github.emusute1212.makasetechoice.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.emusute1212.makasetechoice.MakaseteChoiceApplication
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase

@Module
object AppModule {
    @Provides
    fun provideDatabase(app: MakaseteChoiceApplication): MakaseteChoiceDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            MakaseteChoiceDatabase::class.java,
            "makasete_choice_database"
        ).build()
    }
}