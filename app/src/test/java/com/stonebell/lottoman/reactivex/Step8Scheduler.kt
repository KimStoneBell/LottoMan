package com.stonebell.lottoman.reactivex

import com.stonebell.lottoman.reactivex.common.RxLog
import hu.akarnokd.rxjava2.math.MathFlowable
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.Executors
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
class Step8Scheduler {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun basicTest() {
        Observable.fromIterable((1..5))
                .subscribeOn(Schedulers.newThread())
                .doOnNext { RxLog.d("next : $it") }
                .observeOn(Schedulers.newThread())
                .subscribe { RxLog.d("result : $it") }

        Thread.sleep(1000)
    }

    @Test
    fun newThreadSchedulerTest(){

        Observable.fromIterable((1..5))
                .doOnNext { RxLog.d("original : $it") }
                .map { "<<$it>>" }
//                .subscribeOn(Schedulers.newThread())
                .subscribe { RxLog.d("result : $it") }

//        Thread.sleep(500)

        Observable.fromIterable((1..5))
                .doOnNext { RxLog.d("original : $it") }
                .map { "##$it##" }
//                .subscribeOn(Schedulers.newThread())
                .subscribe { RxLog.d("result : $it") }

        Thread.sleep(500)
    }

    @Test
    fun computationSchedulerTest(){
        val source = Observable.create<String>{
            it.onNext("1")
            Thread.sleep(100)
            it.onNext("2")
            Thread.sleep(100)
            it.onNext("3")
            Thread.sleep(100)
            it.onNext("4")
            Thread.sleep(100)
            it.onNext("5")
            Thread.sleep(100)
            it.onComplete()
        }

        source.map { "<<$it>>" }
//                .subscribeOn(Schedulers.computation())
                .subscribe { RxLog.d("result1 : $it") }


        source.map { "##$it##" }
//                .subscribeOn(Schedulers.computation())
                .subscribe { RxLog.d("result2 : $it") }

        Thread.sleep(600)
    }

    @Test
    fun ioSchedulerTest(){
        val source = Observable.create<String>{
            it.onNext("1")
            Thread.sleep(100)
            it.onNext("2")
            Thread.sleep(100)
            it.onNext("3")
            Thread.sleep(100)
            it.onNext("4")
            Thread.sleep(100)
            it.onNext("5")
            Thread.sleep(100)
            it.onComplete()
        }

        source.map { "<<$it>>" }
                .subscribeOn(Schedulers.io())
                .subscribe { RxLog.d("result1 : $it") }


        source.map { "##$it##" }
                .subscribeOn(Schedulers.io())
                .subscribe { RxLog.d("result2 : $it") }

        Thread.sleep(600)
    }

    @Test
    fun ioSchedulerExample(){
        val root = "C:\\"
        val files = File(root).listFiles().toList()

        val source = Observable.fromIterable(files)
                .filter { it.isDirectory }
                .map { it.absolutePath }


        source.subscribe { RxLog.d("result2 : $it" ) }

        Thread.sleep(1000)

        source.subscribeOn(Schedulers.io())
                .subscribe { RxLog.d("result1 : $it" ) }
        Thread.sleep(1000)
    }

    @Test
    fun trampolineSchedulerTest(){
        val source = Observable.create<String>{
            it.onNext("1")
            Thread.sleep(100)
            it.onNext("2")
            Thread.sleep(100)
            it.onNext("3")
            Thread.sleep(100)
            it.onNext("4")
            Thread.sleep(100)
            it.onNext("5")
            Thread.sleep(100)
            it.onComplete()
        }

        source.map { "<<$it>>" }
                .subscribeOn(Schedulers.trampoline())
                .subscribe { RxLog.d("result1 : $it") }


        source.map { "##$it##" }
                .subscribeOn(Schedulers.trampoline())
                .subscribe { RxLog.d("result2 : $it") }

        Thread.sleep(600)
    }


    @Test
    fun singleSchedulerTest(){
        val source = Observable.create<String>{
            it.onNext("1")
            Thread.sleep(100)
            it.onNext("2")
            Thread.sleep(100)
            it.onNext("3")
            Thread.sleep(100)
            it.onNext("4")
            Thread.sleep(100)
            it.onNext("5")
            Thread.sleep(100)
            it.onComplete()
        }

        source.map { "<<$it>>" }
                .subscribeOn(Schedulers.single())
                .subscribe { RxLog.d("result1 : $it") }


        source.map { "##$it##" }
                .subscribeOn(Schedulers.single())
                .subscribe { RxLog.d("result2 : $it") }

        Thread.sleep(600)
    }


    @Test
    fun executerSchedulerTest(){
        val source = Observable.create<String>{
            it.onNext("1")
            Thread.sleep(100)
            it.onNext("2")
            Thread.sleep(100)
            it.onNext("3")
            Thread.sleep(100)
            it.onNext("4")
            Thread.sleep(100)
            it.onNext("5")
            Thread.sleep(100)
            it.onComplete()
        }

        val executor = Executors.newFixedThreadPool(10)
        source.map { "<<$it>>" }
                .subscribeOn(Schedulers.from(executor))
                .subscribe { RxLog.d("result1 : $it") }


        source.map { "##$it##" }
                .subscribeOn(Schedulers.from(executor))
                .subscribe { RxLog.d("result2 : $it") }

        Thread.sleep(600)
    }
}