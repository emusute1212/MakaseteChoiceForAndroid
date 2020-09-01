package io.github.emusute1212.makasetechoice.di.modules.repositorys

import dagger.Module
import dagger.Provides
import io.github.emusute1212.makasetechoice.data.db.MakaseteChoiceDatabase
import io.github.emusute1212.makasetechoice.data.repository.GroupDataRepository
import javax.inject.Singleton

@Module
class GroupDataRepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(database: MakaseteChoiceDatabase): GroupDataRepository {
        return GroupDataRepository(database)
    }
}