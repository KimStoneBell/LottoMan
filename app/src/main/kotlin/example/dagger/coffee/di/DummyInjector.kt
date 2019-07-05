package example.dagger.coffee.di

import example.dagger.coffee.struct.coffee.CoffeeMaker
import example.dagger.coffee.struct.coffee.CoffeeMakerProperties
import example.dagger.coffee.struct.coffee.heater.A_HeaterImpl
import example.dagger.coffee.struct.coffee.pump.A_PumpImpl

class DummyInjector {
    companion object {
        fun provideHeater() = A_HeaterImpl()
        fun providePump() = A_PumpImpl(provideHeater())

        fun provideCoffeeMaker() = CoffeeMaker(provideHeater(), providePump())

        fun provideCoffeeMakerProperties() = CoffeeMakerProperties()
    }
}