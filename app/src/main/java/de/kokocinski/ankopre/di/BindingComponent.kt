package de.kokocinski.ankopre.di

import android.databinding.DataBindingComponent
import dagger.Component
import dagger.Module

@Component(modules = arrayOf(BindingModule::class))
interface BindingComponent : DataBindingComponent

@Module
class BindingModule