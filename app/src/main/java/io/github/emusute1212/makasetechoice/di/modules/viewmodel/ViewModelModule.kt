package io.github.emusute1212.makasetechoice.di.modules.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.github.emusute1212.makasetechoice.ViewModelFactory

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}