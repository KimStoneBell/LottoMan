package com.stonebell.lottoman.reactivex

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.BiFunction
import io.reactivex.internal.operators.observable.ObservableFromArray
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.reactivestreams.Publisher
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class Step1ObservableTests {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun justTest(){
        Observable.just("hello!?")
                .subscribe({ data -> System.out.println("this is one : $data") })

        Observable.just("hello", "hello1", "hello2", "hello3")
                .map({ data: String -> (data + "add String") })
                .subscribe({ System.out.println("this is two : $it") })
    }

    @Test
    fun createTest(){
       Observable.create<String>{
            it.onNext("hello")
            it.onNext("hello1")
            it.onNext("hello2")
            it.onNext("hello3")
            it.onError(Throwable("error1"))
        }.subscribe({ println("print next = $it") }, { println("print error : ${it.message}") })

    }

    @Test
    fun fromArrayTest(){
        val testString = listOf("hello", "hello1", "hello2", "hello3")
        val observable = Observable.fromArray(testString)

        observable.subscribe({ println("array : $it") })

        val listObservable = observable.flatMapIterable { it }
        listObservable.subscribe({ println("flatMapIterable : $it") })

        val splitListObservale = listObservable.flatMap { Observable.fromArray(it.split("")).flatMapIterable { it } }

        splitListObservale.subscribe({ println("split : $it") })

        splitListObservale.zipWith(Observable.range(1, Int.MAX_VALUE), BiFunction<String, Int, String> { t1, t2 -> "$t1 _ $t2" })
                .subscribe({ println("zipWith : $it") })
    }


    @Test
    fun callAbleTest() {
        Observable.fromCallable { "hello" }
                .subscribe(System.out::println)
    }

    @Test
    fun fromFutureTest(){
        Observable.fromFuture(Executors.newSingleThreadExecutor().submit <String>{
            Thread.sleep(1000)
            return@submit "hello"
        }).subscribe(System.out::println)
    }

    @Test
    fun fromPublisherTest(){
        Observable.fromPublisher <String>{
            it.onNext("onNext")
            it.onComplete()
        }.subscribe(System.out::println)

    }

    @Test
    fun singleTest(){
        Observable.just("hello Single")
                .single("default")
                .subscribe({data -> System.out.println(data)})

        Observable.empty<String>()
                .single("default")
                .subscribe({data -> System.out.println(data)})

        Observable.fromArray((1..5)).flatMapIterable { it }
                .single(10)
                .subscribe({data -> System.out.println(data)})
    }

    @Test
    fun asyncSubjectTest(){
        val subject = AsyncSubject.create<String>()
        subject.subscribe({println("subScribe1 : $it")})
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.subscribe({println("subScribe2 : $it")})
        subject.onNext("4")
        subject.onComplete()
        subject.onNext("5")
        subject.subscribe({println("subScribe3 : $it")})

        val array = arrayOf("1","2","3","4","5")
//        val observable = Observable.just("1","2","3","4","5")
        val observable = Observable.fromArray(array)
        val subject1 = AsyncSubject.create<Array<String>>()
        subject1.subscribe({ println("Subscriber #1 = $it")})
        observable.subscribe(subject1 as Observer<in Array<String>>)
    }

    @Test
    fun behaviorSubjectTest(){
        val subject = BehaviorSubject.createDefault("default")
        subject.subscribe({println("subScribe1 : $it")})
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.subscribe({println("subScribe2 : $it")})
        subject.onNext("4")
        subject.onComplete()
        subject.onNext("5")
        subject.subscribe({println("subScribe3 : $it")})
    }

    @Test
    fun publishSubjectTest(){
        val subject = PublishSubject.create<String>()
        subject.subscribe({println("subScribe1 : $it")})
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.subscribe({println("subScribe2 : $it")})
        subject.onNext("4")
        subject.onComplete()
        subject.onNext("5")
        subject.subscribe({println("subScribe3 : $it")})
    }


    @Test
    fun replaySubjectTest(){
        val subject = ReplaySubject.create<String>()
        subject.subscribe({println("subScribe1 : $it")})
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.subscribe({println("subScribe2 : $it")})
        subject.onNext("4")
        subject.onComplete()
        subject.onNext("5")
        subject.subscribe({println("subScribe3 : $it")})
    }

    @Test
    fun connectableObserveable(){
        val dt = arrayOf("1", "3", "5")
        val balls = Observable.interval(1000, TimeUnit.MILLISECONDS).map { dt[it.toInt()] }.take(dt.size.toLong())
        val source = balls.publish()
        println("start publish!")
        source.subscribe { println("subscribe#1 : $it") }
        source.subscribe { println("subscribe#2 : $it") }
        Thread.sleep(2000)

        source.connect()

        Thread.sleep(2500)

        source.subscribe { println("subscribe#3 : $it") }

        Thread.sleep(1000)

        source.subscribe { println("subscribe#4 : $it") }
    }
}