package com.stonebell.lottoman.reactivex

import com.stonebell.lottoman.reactivex.common.RxLog
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
class Step6OperatorsCondition {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun ambTest() { // diff flatMap
        val sources = arrayListOf(Observable.fromArray(arrayListOf("6", "7", "4", "2"))
                .flatMapIterable { it }
                .doOnComplete{RxLog.d("complite array1")}
                ,
                Observable.fromArray(arrayListOf("-d", "-s", "-p"))
                .flatMapIterable { it }
                .zipWith(Observable.interval(150, 200, TimeUnit.MILLISECONDS),{data: String, notUsed: Long -> data})
                .doOnComplete{RxLog.d("complite array2")}
        )
        Observable.amb(sources)
                .doOnComplete{RxLog.d("complite all")}
                .subscribe { RxLog.d(it) }

        Thread.sleep(1000)
    }


    @Test
    fun takeUntilTest() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .map { (it + 1).toString() }
                .take(6)
                .doOnComplete{RxLog.d("complite interval")}
                .takeUntil(Observable.timer(500, TimeUnit.MILLISECONDS))
                .doOnComplete{RxLog.d("complite all")}
                .subscribe { RxLog.d(it) }
        Thread.sleep(1000)
    }

    @Test
    fun skipUntilTest() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .map { (it + 1).toString() }
                .take(6)
                .doOnComplete{RxLog.d("complite interval")}
                .skipUntil(Observable.timer(500, TimeUnit.MILLISECONDS))
                .doOnComplete{RxLog.d("complite all")}
                .subscribe { RxLog.d(it) }
        Thread.sleep(1000)
    }

    @Test
    fun allTest(){
        val values1 = arrayListOf("a:0","a:1","a:2","a:3","a:4","a:5","a:6","a:7")
        val values2 = arrayListOf("a:0","a:1","a:2","a:3","a:4","a:5","a:6","b:7")

        Observable.fromIterable(values1)
                .all { it.startsWith("a:") }
                .subscribe{value: Boolean -> println("$value")}


        Observable.fromIterable(values2)
                .all { it.startsWith("a:") }
                .subscribe{value: Boolean -> println("$value")}
    }
}