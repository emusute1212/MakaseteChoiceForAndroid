package io.github.emusute1212.makasetechoice.di.modules.fragments

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.emusute1212.makasetechoice.groups.choice.ChoiceDialogFragment

@Module
interface ChoiceDialogFragmentModule {
    @ContributesAndroidInjector
    fun contributesChoiceDialogFragment(): ChoiceDialogFragment
}