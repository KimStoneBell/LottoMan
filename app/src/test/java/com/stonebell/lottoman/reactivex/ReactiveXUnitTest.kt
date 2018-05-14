package com.stonebell.lottoman.reactivex

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import kotlin.text.Typography.times


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ReactiveXUnitTest : KotlinTests(){
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun subscribeFunctionTest(){
        Flowable.just("hello!?").subscribe({ System.out.println(it)})

        Flowable.just("hello!?").subscribe(System.out::println)

        Flowable.just("hello!?").subscribe(this::printTest)
    }

    fun printTest(text: String){
        System.out.println(text)
    }

    @Test
    fun observableTest() {

        val observable = Observable.just("hello!?")
        observable.subscribe({ data -> System.out.println("this is one : $data")})
        observable.map({data:String -> (data + "add String")}).subscribe({ System.out.println("this is two : $it")})

        var abc = "hello"

//        val observer = Observable.create<String>{
//            it.onNext("hello")
//            it.onNext("hello1")
//            it.onNext("hello2")
//            it.onNext("hello3")
//            it.onError(Throwable("error1"))
//        }

//        val observer = Observable.just("hello", "hello1", "hello2", "hello3")
        val testString = arrayListOf("hello","hello1","hello2","hello3")
        val observer = Observable.just("hello", "hello1", "hello2", "hello3")

        observer.subscribe({println("print next = $it")}, { println("print error : ${it.message}")})

        observer.also { println("also")}.let { println("let")}

        observer.subscribe({println("print2 next = $it")}, { println("print2 error : ${it.message}")})

    }

    @Test fun filterTest(){
        val observer = Observable.just("hello", "hello1", "hello2", "hello3")

        observer.filter{it == "hello"}.map{it+"_index"}.subscribe({println("print : ${it == "hello"} , $it")})
    }

    @Test fun threadTest(){
        val observer = Observable.just("hello", "hello1", "hello2", "hello3")

        observer.subscribe({println("print : $it")})

        observer.map { it + ")" }

        var observarString = "hello1"
        val observer2 = Observable.fromCallable {  observarString }

        observer2.subscribe({println("print3 : $it")})

        observarString = "test2"

    }

    @Test fun testCreate() {
        Observable.create<String> { onSubscribe ->
            onSubscribe.onNext("Hello")
            onSubscribe.onComplete()
        }.subscribeBy(
                onError = {
                    a.received(it)
                println(it)}
        )

        verify(a, times(1)).received("Hello")
    }

}