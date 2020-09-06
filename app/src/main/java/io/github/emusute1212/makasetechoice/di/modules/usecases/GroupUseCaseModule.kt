package io.github.emusute1212.makasetechoice.di.modules.usecases

import dagger.Module
import dagger.Provides
import io.github.emusute1212.makasetechoice.data.repository.GroupDataRepository
import io.github.emusute1212.makasetechoice.usecase.GroupUseCase

@Module
class GroupUseCaseModule {
    @Provides
    fun provideGroupUseCase(repository: GroupDataRepository): GroupUseCase {
        return GroupUseCase(repository)
    }
}