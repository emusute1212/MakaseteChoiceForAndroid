package io.github.emusute1212.makasetechoice.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.emusute1212.makasetechoice.MakaseteChoiceApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MakaseteChoiceApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: MakaseteChoiceApplication): Builder

        fun build(): ApplicationComponent
    }
}