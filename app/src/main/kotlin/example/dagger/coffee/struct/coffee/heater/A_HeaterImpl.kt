package example.dagger.coffee.struct.coffee.heater

import example.dagger.coffee.di.modules.heater.IHeater

class A_HeaterImpl : IHeater{
    override var isHot: Boolean = false

    override fun on() {
        System.out.println("A_Heater on")
        isHot = true
    }

    override fun off() {
        System.out.println("A_Heater off")
        isHot = false
    }

}