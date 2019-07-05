package example.dagger.coffee.di.modules

import dagger.Module
import dagger.Provides
import example.dagger.coffee.di.modules.heater.IHeater
import example.dagger.coffee.di.modules.pump.IPump
import example.dagger.coffee.struct.heater.A_HeaterImpl
import example.dagger.coffee.struct.pump.A_PumpImpl

@Module
class CoffeeModule{
    @Provides
    fun provideHeater() : IHeater = A_HeaterImpl()

    @Provides
    fun providePump() : IPump = A_PumpImpl(provideHeater())
}