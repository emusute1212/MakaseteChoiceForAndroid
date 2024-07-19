package io.github.emusute1212.makasetechoice

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MakaseteChoiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}