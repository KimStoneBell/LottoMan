package example.dagger.coffee.struct

import example.dagger.coffee.di.modules.heater.IHeater
import example.dagger.coffee.di.modules.pump.IPump
import javax.inject.Inject

class CoffeeMaker @Inject constructor(val heater : IHeater, val pump : IPump){
    fun brew(){
        heater.on()
        pump.pump()
        System.out.println(" [_]P coffee! [_]P ")
        heater.off()
    }
}