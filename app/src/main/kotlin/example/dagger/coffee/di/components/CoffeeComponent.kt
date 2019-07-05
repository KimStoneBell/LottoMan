package example.dagger.coffee.di.components

import dagger.Component
import example.dagger.coffee.di.modules.CoffeeModule
import example.dagger.coffee.struct.coffee.CoffeeMaker
import example.dagger.coffee.struct.coffee.CoffeeMakerProperties

@Component(modules = [CoffeeModule::class])
interface CoffeeComponent{
    fun getCoffeeMaker() : CoffeeMaker
    fun getCoffeeMakerProperties() : CoffeeMakerProperties

    fun injectCoffeeMakerProperties( coffeeMakerProperties: CoffeeMakerProperties)
}