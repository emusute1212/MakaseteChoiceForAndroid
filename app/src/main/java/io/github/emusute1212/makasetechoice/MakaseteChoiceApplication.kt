package io.github.emusute1212.makasetechoice

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.emusute1212.makasetechoice.di.DaggerApplicationComponent

class MakaseteChoiceApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().app(this).build()
    }
}