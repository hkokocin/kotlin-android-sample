package de.kokocinski.ankopre.di

import dagger.Component
import de.kokocinski.ankopre.data.Webservice
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun webservice(): Webservice
}