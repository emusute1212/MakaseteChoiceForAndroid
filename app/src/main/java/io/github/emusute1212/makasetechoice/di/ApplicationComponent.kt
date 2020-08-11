package io.github.emusute1212.makasetechoice.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.emusute1212.makasetechoice.MakaseteChoiceApplication
import io.github.emusute1212.makasetechoice.di.modules.activity.MainActivityModule
import io.github.emusute1212.makasetechoice.di.modules.fragments.MembersFragmentModule
import io.github.emusute1212.makasetechoice.di.modules.repositorys.MemberDataRepositoryModule
import io.github.emusute1212.makasetechoice.di.modules.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainActivityModule::class,
        MembersFragmentModule::class,
        MemberDataRepositoryModule::class,
        ViewModelModule::class,
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