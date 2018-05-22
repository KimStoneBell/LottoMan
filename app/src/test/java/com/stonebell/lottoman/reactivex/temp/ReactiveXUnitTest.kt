package com.stonebell.lottoman.reactivex.temp

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import org.junit.Assert
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ReactiveXUnitTest : KotlinTests() {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun subscribeFunctionTest() {
        Flowable.just("hello!?").subscribe({ System.out.println(it) })

        Flowable.just("hello!?").subscribe(System.out::println)

        Flowable.just("hello!?").subscribe(this::printTest)
    }

    fun printTest(text: String) {
        System.out.println(text)
    }

    @Test
    fun observableTest() {
        var abc = "hello"
    }

    @Test
    fun filterTest() {
        val observer = Observable.just("hello", "hello1", "hello2", "hello3")

        observer.filter { it == "hello" }.map { it + "_index" }.subscribe({ println("print : ${it == "hello"} , $it") })
    }

    @Test
    fun threadTest() {
        val observer = Observable.just("hello", "hello1", "hello2", "hello3")

        observer.subscribe({ println("print : $it") })

        observer.map { it + ")" }

        var observarString = "hello1"
        val observer2 = Observable.fromCallable { observarString }

        observer2.subscribe({ println("print3 : $it") })

        observarString = "test2"

    }

    @Test
    fun myObservableTest() {
        var a: String?
        a = null

        Observable.create<String> {
            it.onNext("Hello")
            a!!.split("")

//            it.onError(Throwable("myException"))
            it.onComplete()
        }.subscribe({ println("onNext : $it") }
                , {
                    println(it.message)
                    Observable.fromArray(it.stackTrace.asList()).flatMapIterable { it }.subscribe({ println(it) })
                }
                , { println("onComplite :") })
    }

    fun hello() : Observable<String> = (0..1).toObservable().map { "Simple $ it" }



    interface ITestInterface{
        fun fun1()
        fun fun2()
    }



    val funValue = object : ITestInterface {
        override fun fun1() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun fun2() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}