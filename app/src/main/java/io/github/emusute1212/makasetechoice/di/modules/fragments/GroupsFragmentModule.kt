package io.github.emusute1212.makasetechoice.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.makasetechoice.groups.GroupFragment

@Module
interface GroupsFragmentModule {
    @ContributesAndroidInjector
    fun contributesGroupsFragment(): GroupFragment
}