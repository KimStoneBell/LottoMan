package com.stonebell.lottoman.dagger2

import example.dagger.coffee.di.DummyInjector
import example.dagger.coffee.di.components.CafeComponent
import example.dagger.coffee.di.components.DaggerCafeComponent
import example.dagger.coffee.di.components.DaggerCoffeeComponent
import example.dagger.coffee.struct.coffee.CoffeeBean
import example.dagger.coffee.struct.coffee.CoffeeMaker
import example.dagger.coffee.struct.coffee.CoffeeMakerProperties
import example.dagger.coffee.struct.coffee.heater.A_HeaterImpl
import example.dagger.coffee.struct.coffee.pump.A_PumpImpl
import org.junit.Test


class Dagger2Test {

    @Test
    fun coffeeTest(){
        //No DI
        System.out.println("<<<<<<<<<<<<<<<<  No DI")

        val heater = A_HeaterImpl()
        val pump = A_PumpImpl(heater)

        CoffeeMaker(heater, pump).brew()

        //Simple DI
        System.out.println("\n\n<<<<<<<<<<<<<<<<  Simple DI")
        with(DummyInjector){
            CoffeeMaker(provideHeater(), providePump()).brew()
        }

        System.out.println("\n\n<<<<<<<<<<<<<<<<  Simple DI2")
        DummyInjector.provideCoffeeMaker().brew()

        System.out.println("\n\n<<<<<<<<<<<<<<<<  Simple Dagger construct")
        DaggerCoffeeComponent.create().getCoffeeMaker().brew()

        System.out.println("\n\n<<<<<<<<<<<<<<<<  Simple Dagger properties")
        DaggerCoffeeComponent.create().getCoffeeMakerProperties().brew()

        System.out.println("\n\n<<<<<<<<<<<<<<<<  Simple Dagger properties inject")
        CoffeeMakerProperties().apply {
            DaggerCoffeeComponent.create().injectCoffeeMakerProperties(this)
        }.brew()


    }

    @Test
    fun coffeeTest2(){
        DaggerCafeComponent.create().cafeInfo().welcome()
        DaggerCafeComponent.create().coffeeMaker().brew(CoffeeBean())
    }
}