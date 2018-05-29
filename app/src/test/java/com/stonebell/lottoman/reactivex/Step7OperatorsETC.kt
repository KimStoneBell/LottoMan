package com.stonebell.lottoman.reactivex

import com.stonebell.lottoman.reactivex.common.RxLog
import hu.akarnokd.rxjava2.math.MathFlowable
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.zipWith
import org.junit.Assert
import org.junit.Test
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 *
 * Observable sum
 */
class Step7OperatorsETC {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun mathTest() {
        Observable.fromIterable((1..4))
                .count()
                .subscribe {it: Long -> println(it)}

        Flowable.fromIterable((1..4))
                .to { MathFlowable.max(it) }
                .subscribe {println(it)}

        Flowable.fromIterable((1..4))
                .to { MathFlowable.min(it) }
                .subscribe {println(it)}

        Flowable.fromIterable((1..4))
                .to { MathFlowable.sumInt(it) }
                .subscribe {println(it)}

        Observable.fromIterable((1..4))
                .toFlowable(BackpressureStrategy.BUFFER)
                .to { MathFlowable.averageDouble(it) }
                .subscribe {println(it)}
    }

    @Test
    fun delayTest(){
        Observable.fromIterable((1..5))
                .delay(1, TimeUnit.SECONDS)
                .subscribe { RxLog.d(it.toString()) }

        RxLog.d("start")
        Thread.sleep(2000)
        RxLog.d("end")
    }

    @Test
    fun timeIntervalTest(){
        val source = Observable.interval(100,TimeUnit.MILLISECONDS)
                .delay(1, TimeUnit.SECONDS).take(5)


        source.timeInterval()
                .subscribe { RxLog.d("subscribe1 : ${it}")}

        source.subscribe { RxLog.d("subscribe2 : ${it}") }

        RxLog.d("start")
        Thread.sleep(2000)
        RxLog.d("end")
    }

}