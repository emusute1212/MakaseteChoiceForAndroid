package io.github.emusute1212.makasetechoice.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.emusute1212.makasetechoice.ViewModelFactory
import io.github.emusute1212.makasetechoice.di.ViewModelKey
import io.github.emusute1212.makasetechoice.groups.GroupsViewModel
import io.github.emusute1212.makasetechoice.members.MembersViewModel
import io.github.emusute1212.makasetechoice.splash.SplashScreenViewModel

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MembersViewModel::class)
    fun bindMembersViewModel(viewModel: MembersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GroupsViewModel::class)
    fun bindGroupsViewModel(viewModel: GroupsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel
}