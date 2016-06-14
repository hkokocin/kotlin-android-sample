package de.kokocinski.ankopre

import android.app.Application
import de.kokocinski.ankopre.di.ApplicationComponent
import de.kokocinski.ankopre.di.ApplicationModule
import de.kokocinski.ankopre.di.DaggerApplicationComponent

class SampleApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}