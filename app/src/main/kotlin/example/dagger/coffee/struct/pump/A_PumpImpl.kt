package example.dagger.coffee.struct.pump

import example.dagger.coffee.di.modules.heater.IHeater
import example.dagger.coffee.di.modules.pump.IPump

class A_PumpImpl(val heater : IHeater) : IPump{
    override fun pump() {
        if(heater.isHot){
            System.out.println("A_Pump => => pumping => =>")
        }
        else{
            System.out.println("A_Pump => no Hot!!")
        }
    }

}