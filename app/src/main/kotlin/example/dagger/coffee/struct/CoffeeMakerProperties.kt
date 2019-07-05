package example.dagger.coffee.struct

import example.dagger.coffee.di.modules.heater.IHeater
import example.dagger.coffee.di.modules.pump.IPump
import javax.inject.Inject

class CoffeeMakerProperties @Inject constructor(){

    @Inject lateinit var  heater : IHeater
    @Inject lateinit var pump : IPump

    fun brew(){
        heater.on()
        pump.pump()
        System.out.println("CoffeeMakerProperties :  [_]P coffee! [_]P ")
        heater.off()
    }
}