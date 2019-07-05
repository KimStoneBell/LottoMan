package example.dagger.coffee.di.modules.heater

interface IHeater{

    var isHot : Boolean

    fun on()
    fun off()
}