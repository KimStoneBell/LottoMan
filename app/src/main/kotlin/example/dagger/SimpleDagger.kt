package example.dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Inject

interface IFruits{
    fun getName() : String
}

interface IColor{
    fun getColor() : Int
}

class Banana @Inject constructor(var color : IColor) : IFruits{

    override fun getName(): String {
        return "Banana ${color.getColor()}"
    }
}

class Red @Inject constructor(): IColor{
    override fun getColor(): Int {
        return 0
    }
}

@Module
abstract class FruitsModule{
    @Binds
    abstract fun getFruits(fruits: Banana) : IFruits

    @Binds
    abstract fun getColor(color : Red) : IColor
}

@Component(modules = [FruitsModule::class])
interface SimpleDagger{
    fun getFruits() : IFruits
    fun getColor() : IColor
//    fun injectBanana(banana : Banana)
}