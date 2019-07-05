package example.dagger.coffee.struct.coffee.pump

import example.dagger.coffee.di.modules.heater.IHeater
import example.dagger.coffee.di.modules.pump.IPump
import javax.inject.Inject

class A_PumpImpl @Inject constructor(val heater : IHeater) : IPump{
    override fun pump() {
        if(heater.isHot){
            System.out.println("A_Pump => => pumping => =>")
        }
        else{
            System.out.println("A_Pump => no Hot!!")
        }
    }

}