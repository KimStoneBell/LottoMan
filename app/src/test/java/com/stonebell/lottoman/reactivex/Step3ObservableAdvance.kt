package com.stonebell.lottoman.reactivex

import android.util.Log
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
 */
class Step3ObservableAdvance {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun intervarTest(){
//        Observable.interval(100L, TimeUnit.MILLISECONDS)
        Observable.interval(0,100L, TimeUnit.MILLISECONDS)
                .map { (it + 1) * 100}
                .take(5)
                .subscribe{RxLog.d("$it")}
        RxLog.d("sleep start!")
        Thread.sleep(1000)
        RxLog.d("end!")

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map { (it + 1) * 100}
                .take(5)
                .subscribe{RxLog.d("$it")}
        RxLog.d("sleep start!")
        Thread.sleep(1000)
        RxLog.d("end!")
    }

    @Test
    fun timerTest(){
        Observable.timer(500L, TimeUnit.MILLISECONDS)
                .subscribe{RxLog.d("$it")}

        RxLog.d("sleep start!")
        Thread.sleep(1000)
        RxLog.d("end!")
    }

    @Test
    fun rangeTest(){
        Observable.range(0, 10)
                .filter{(it % 2) == 0}
                .subscribe{println(it)}
    }

    @Test
    fun interverRangeTest(){
        Observable.intervalRange(1, 5, 0, 100, TimeUnit.MILLISECONDS)
                .subscribe{RxLog.d("$it")}

        RxLog.d("sleep start!")
        Thread.sleep(1000)
        RxLog.d("end!")

        Observable.interval(0, 100, TimeUnit.MILLISECONDS)
                .map { it + 1}
                .take(5)
                .subscribe{RxLog.d("$it")}

        RxLog.d("sleep start!")
        Thread.sleep(1000)
        RxLog.d("end!")
    }

    @Test
    fun deferTest(){
        val colorIterator = arrayListOf("1","3","5","7").iterator()
        val supplier = {getObservable(colorIterator)}

        val source = Observable.defer(supplier)

        source.subscribe { RxLog.d(it) }
        source.subscribe { RxLog.d(it) }

    }

    fun getObservable(iterator: Iterator<String>): Observable<String>{
        println("iterator : ${iterator.hashCode()}")
        if(iterator.hasNext()){
            val color = iterator.next()
            return Observable.just(
                "${color}"
                    ,"${color}-R"
                    ,"${color}-P"
            )
        }
        return Observable.empty()
    }

    @Test
    fun repeatTest(){
        val balls = arrayListOf("1", "3", "5")

        Observable.fromArray(balls).flatMapIterable{ it }
                .repeat(3)
                .doOnComplete { RxLog.d("onComplite") }
                .subscribe{RxLog.d(it)}
    }

    @Test
    fun heartBeatExample(){
        val serverUrl = "https://api.github.com/zen"
        val source = Observable.interval(1,TimeUnit.SECONDS)
                .map { serverUrl }
                .map { Thread.sleep(1000) }
                .repeat()
                source.subscribe { RxLog.d("ping interval!! $it") }

        RxLog.d("sleep start!")
        Thread.sleep(5000)
        RxLog.d("sleep end!")
        Observable.timer(1,TimeUnit.SECONDS)
                .map { serverUrl }
                .map { Thread.sleep(1000) }
                .repeat()
                .subscribe { RxLog.d("ping timer!! $it") }

        Thread.sleep(5000)
    }
}