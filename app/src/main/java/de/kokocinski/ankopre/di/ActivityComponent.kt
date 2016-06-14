package de.kokocinski.ankopre.di

import dagger.Component
import de.kokocinski.ankopre.main.MainActivity

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ActivityComponent {
    fun injectTo(mainActivity: MainActivity)
}