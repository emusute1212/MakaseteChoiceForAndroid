package io.github.emusute1212.makasetechoice.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.makasetechoice.members.MembersFragment

@Module
interface MembersFragmentModule {
    @ContributesAndroidInjector
    fun contributesMembersFragment(): MembersFragment
}