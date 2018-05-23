package com.stonebell.lottoman.reactivex

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class Step2OperatorsTests {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun mapTest() {
        Observable.just(1,2,3,4,5)
                .map { "$it hello" }
                .subscribe({println("print : $it")})
    }

    @Test
    fun flatMapTest(){
        Observable.just(1,2,3,4,5)
                .flatMap { Observable.just("$it{}", "$it{}2") }
                .subscribe({println("print : $it")})
    }

    @Test
    fun gugudanKotlinTest(){
        val input = Scanner(System.`in`)
        print("input : ")
        val dan = input.nextLine().toInt()
        for(row in 0..9){
            println("$dan * row = ${dan * row}")
        }
    }

    @Test
    fun gugudanRxTest(){
        val dan = 2
        Observable.just(2)
                .flatMap<String> {
                    Observable.range(1,9).map<String> { "$dan * $it = ${dan * it}" }
                }
                .subscribe{
                    println(it)
                }

        Observable.just(2)
                .flatMap({Observable.range(1,9)}, {item: Int, range: Int -> "$item * $range = ${item * range}"})
                .subscribe{
                    println(it)
                }
    }

    @Test
    fun filterTest(){
//        Observable.range(0, 10)
//                .filter{ it % 2 == 0}
//                .subscribe{println(it)}

        Observable.fromArray((1..10)).flatMapIterable { it }
                .filter{ it % 2 == 0}
                .subscribe{println(it)}

        val source = Observable.fromArray((1..10)).flatMapIterable { it }

        var single = source.first(-1)
        single.subscribe{result: Int -> println("first : $result")}

        single = source.last(-1)
        single.subscribe{result: Int -> println("last : $result")}

        var observable = source.take(3)
        observable.subscribe{result: Int -> println("take : $result")}

        observable = source.takeLast(3)
        observable.subscribe{result: Int -> println("takeLast : $result")}

        observable = source.skip(7)
        observable.subscribe{result: Int -> println("skip : $result")}


        observable = source.skipLast(7)
        observable.subscribe{result: Int -> println("skipLast : $result")}

    }

    @Test
    fun reduceTest(){
        val source = Observable.fromArray((1..10)).flatMapIterable { it }

        source.reduce({dat: Int, dat2: Int -> dat + dat2})
                .subscribe { println("print : $it") }
    }

    @Test
    fun storeExample(){

        //1. 전체 매출 데이터를 입력함..
//        val sellProduct = ArrayList<Product>();
//        sellProduct.add(Product("TV", 2500))
//        sellProduct.add(Product("Camera", 300))
//        sellProduct.add(Product("TV", 1600))
//        sellProduct.add(Product("Phone", 800))

        val sellProduct = ArrayList<Pair<String, Int>>();
        sellProduct.add(Pair("TV", 2500))
        sellProduct.add(Pair("Camera", 300))
        sellProduct.add(Pair("TV", 1600))
        sellProduct.add(Pair("Phone", 800))

        //2. 매출 데이터 중 TV매출을 필터링함.
        val source = Observable.fromArray(sellProduct).flatMapIterable { it }
                .filter{it.first == "TV"}
                .map { it.second }

        //3. TV 매출의 합을 구함.
        source.reduce{t1: Int, t2: Int ->  t1 + t2}.subscribe{ println("sum TV: ${it}")}
    }

    class Product(val name: String,val price: Int)

}