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
class Step5OperatorsCombination {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun zipTest() { // diff flatMap

        Observable.zip(
                Observable.just("1","2","3")
        ,Observable.just("-1","-2","-3")
        , BiFunction{data1: String, data2: String -> data1 + data2}
        ).subscribe{ println(it)}


        Observable.zip(
                Observable.just("1","2","3")
                ,Observable.interval(200,TimeUnit.MILLISECONDS)
                , BiFunction{data1: String, data2: Long -> data1}
        ).subscribe{ RxLog.d(it)}

        Thread.sleep(1000)
    }


    @Test
    fun electricBillsTest(){
        val values = Observable.fromArray(arrayListOf(100, 300)).flatMapIterable { it }

        val basePrice = values
                .map {
                    when{
                    it <= 200 ->  910
                    it <= 400 ->  1600
                    else -> 7300
                    }
                }

        val usagePrice = values
                .map {
                    when{
                    it <= 200 ->  it*93.3
                    it <= 400 ->  it*187.9
                    else -> it*280.6
                    }
                }

        val sumPrice = Observable.zip(basePrice, usagePrice, BiFunction{ data1: Int, data2: Double -> (data1 + data2).toInt()})
                .map { DecimalFormat("#,###").format(it) }


        Observable.zip(sumPrice, values, BiFunction{ data1: String, data2: Int -> "data : $data2 , totalPrice : $data1"})
                .subscribe { println(it) }

        sumPrice.zipWith(values, BiFunction{ data1: String, data2: Int -> "data : $data2 , totalPrice : $data1"})
                .subscribe{println(it)}
    }

    @Test
    fun combineLatestTest(){
        Observable.combineLatest(
                Observable.fromArray(arrayListOf("6", "7", "4", "2"))
                        .flatMapIterable { it }
                        .zipWith(Observable.interval(100, TimeUnit.MILLISECONDS),{data: String, notUsed: Long -> data})
        , Observable.fromArray(arrayListOf("-d", "-s", "-p"))
                .flatMapIterable { it }
                .zipWith(Observable.interval(150, 200, TimeUnit.MILLISECONDS),{data: String, notUsed: Long -> data})
        , BiFunction{ data1: String, data2: String -> data1 + data2})
                .subscribe { RxLog.d(it) }

        Thread.sleep(5000)

    }


}