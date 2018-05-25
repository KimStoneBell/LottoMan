package com.stonebell.lottoman.reactivex

import com.stonebell.lottoman.reactivex.common.RxLog
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Assert
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 *
 * Observable Data change
 */
class Step4OperatorsChange {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun concatMapTest() { // diff flatMap
        Observable.interval(1, TimeUnit.SECONDS)
                .map { it }
                .take(3)
                .concatMap { data -> Observable.interval(2, TimeUnit.SECONDS).map{"$data , $it"}.take(3) }
                .subscribe({ RxLog.d("print : $it")})
        RxLog.d("start sleep")
        Thread.sleep(15000)

        RxLog.d("end sleep")
    }

    @Test
    fun switchMahpTest(){
        Observable.interval(1, TimeUnit.SECONDS)
                .map { it }
                .take(3)
                .doOnNext { RxLog.d("next = $it") }
                .switchMap { data ->
                    Observable.interval(2, TimeUnit.SECONDS)
                        .map{"$data , $it"}
                        .take(3)
                }
                .subscribe({ RxLog.d("print : $it")})
        RxLog.d("start sleep")
        Thread.sleep(15000)

        RxLog.d("end sleep")
    }

    @Test
    fun groupByTest(){
        val objes = arrayListOf("6","4","2-T","2","6-T","4-T")

        Observable.fromArray(objes)
                .flatMapIterable { it }
                .groupBy { groupSort(it) }
                .filter { it.key == "BALL" }
                .subscribe {obj -> obj.subscribe { println("GROUP: ${obj.key} , Value: $it") } }
    }

    fun groupSort(obj: String) : String{
        when{
            obj.endsWith("-H") -> return "HEXAGON"
            obj.endsWith("-O") -> return "OCTAGON"
            obj.endsWith("-R") -> return "RECTANGLE"
            obj.endsWith("-T") -> return "TRIANGLE"
            obj.endsWith("-D") -> return "DIAMOND"
            else -> return "BALL"
        }
    }

    @Test
    fun scanTest(){ // diff reduce
        val source = Observable.fromArray((1..10)).flatMapIterable { it }

        source.reduce({dat: Int, dat2: Int -> dat + dat2})
                .subscribe { println("print reduce: $it") }


        source.scan({dat: Int, dat2: Int -> dat + dat2})
                .subscribe { println("print scan: $it") }
    }
}