package io.github.emusute1212.makasetechoice.di.modules.repositorys

import dagger.Module
import dagger.Provides
import io.github.emusute1212.makasetechoice.data.repository.MemberDataRepository
import javax.inject.Singleton

@Module
class MemberDataRepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(): MemberDataRepository {
        return MemberDataRepository()
    }
}