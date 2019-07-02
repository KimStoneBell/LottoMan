package com.stonebell.lottoman.dagger2

import example.dagger.DaggerSimpleDagger
import org.junit.Test


class Dagger2Test {
    @Test
    fun daggerTest(){

        System.out.println(DaggerSimpleDagger.create().getFruits().getName())


//        val banana = Banana()
//        DaggerSimpleDagger.create().injectBanana(banana)
//        System.out.println(banana)
    }
}