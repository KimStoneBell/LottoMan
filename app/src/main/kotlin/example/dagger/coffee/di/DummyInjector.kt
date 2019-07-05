package example.dagger.coffee.di

import example.dagger.coffee.struct.CoffeeMaker
import example.dagger.coffee.struct.CoffeeMakerProperties
import example.dagger.coffee.struct.heater.A_HeaterImpl
import example.dagger.coffee.struct.pump.A_PumpImpl

class DummyInjector {
    companion object {
        fun provideHeater() = A_HeaterImpl()
        fun providePump() = A_PumpImpl(provideHeater())

        fun provideCoffeeMaker() = CoffeeMaker(provideHeater(), providePump())

        fun provideCoffeeMakerProperties() = CoffeeMakerProperties()
    }
}