package com.stonebell.lottoman.reactivex

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.TimeUnit

class KotlinRxExample {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun simpleTest(){
        val observable = Observable.just("it")

        observable.subscribe {
            System.out.println("step1 subscribe data : $it")
        }
        Thread{}.start()


        Thread.sleep(1000)
        observable.subscribe {
            System.out.println("step2 subscribe data : $it")
        }
    }

    @Test
    fun justTest(){

        val intentsSubject: PublishSubject<String> = PublishSubject.create()

//        val dataObservable = Observable.interval( 1, TimeUnit.SECONDS)
//                        .map { "map : " + it }
//                .doOnNext { System.out.println("dataObservable data : $it") }
////        dataObservable.subscribe(intentsSubject)
//        val observableData1 = Observable.fromCallable { method() }.doOnNext { System.out.println("onNext obData!! $it") }
        val observableData2 = Observable.just("it")

        intentsSubject.doOnNext { System.out.println("intentsSubject data : $it") }.subscribe{System.out.println("intentsSubject subscribe : $it")}


        intentsSubject.onNext("toto1")

        observableData2.subscribe(intentsSubject) // < 서브젝트 발행 테스트

        intentsSubject.onNext("toto2")

        Thread.sleep(5000)

//        val observable = observableData
//                .map {"$it hello" }
//                .replay()
//                .autoConnect(0)
////                .cache()
////                .publish()
////                .replay()
////                .autoConnect(0)
////                .cache()
//
////        intentsSubject.onNext(10)
//
//        observableData2.subscribe {
//            System.out.println("subscribe1 : $it")
//        }
//
////        intentsSubject.onNext(10)
//
//        observable.subscribe {
//            System.out.println("subscribe2 : $it")
//        }
//
//        observable.subscribe {
//            System.out.println("subscribe3 : $it")
//        }


    }

    fun method(): String{
        System.out.println("callMethod")
        return "10"
    }
    @Test
    fun justTest2(){
        val observable1 = Observable.fromArray(arrayOf("a1","a2","b1","b2","b3"))




    }
}