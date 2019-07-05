package example.dagger.coffee.di.modules

import dagger.Module
import dagger.Provides
import example.dagger.coffee.struct.cafe.CafeInfo
import example.dagger.coffee.struct.coffee.CoffeeMaker
import example.dagger.coffee.struct.coffee.heater.A_HeaterImpl
import example.dagger.coffee.struct.coffee.pump.A_PumpImpl

@Module
class CafeModule{
    @Provides
    fun provideCafeInfo() = CafeInfo()

    @Provides
    fun provideCoffeMaker() = CoffeeMaker(provideHeater(),providePump())

    @Provides
    fun provideHeater() = A_HeaterImpl()

    @Provides
    fun providePump() = A_PumpImpl(provideHeater())
}