package example.dagger.coffee.di.components

import dagger.Component
import example.dagger.coffee.di.modules.CafeModule
import example.dagger.coffee.struct.coffee.CoffeeMaker
import example.dagger.coffee.struct.cafe.CafeInfo



@Component(modules = [CafeModule::class])
interface CafeComponent{
    fun cafeInfo(): CafeInfo
    fun coffeeMaker(): CoffeeMaker
}