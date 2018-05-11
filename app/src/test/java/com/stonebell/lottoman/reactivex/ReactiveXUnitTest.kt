package com.stonebell.lottoman.reactivex

import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ReactiveXUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun reactXTutorial() {

        Flowable.just("hello!?").subscribe({ System.out.println(it)})

        Flowable.just("hello!?").subscribe(System.out::println)

        Flowable.just("hello!?").subscribe(this::printTest)
    }

    fun printTest(text: String){
        System.out.println(text)
    }

}