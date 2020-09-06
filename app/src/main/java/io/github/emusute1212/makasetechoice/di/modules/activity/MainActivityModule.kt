package io.github.emusute1212.makasetechoice.di.modules.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.makasetechoice.MainActivity

@Module
interface MainActivityModule {
    @ContributesAndroidInjector
    fun contributesMainActivity(): MainActivity
}