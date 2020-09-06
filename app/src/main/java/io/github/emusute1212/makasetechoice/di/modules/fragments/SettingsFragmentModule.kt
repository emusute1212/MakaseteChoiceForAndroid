package io.github.emusute1212.makasetechoice.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.makasetechoice.settings.SettingsFragment

@Module
interface SettingsFragmentModule {
    @ContributesAndroidInjector
    fun contributesSettingsFragment(): SettingsFragment
}