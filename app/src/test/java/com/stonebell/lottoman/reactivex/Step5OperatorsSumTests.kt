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
 * Observable sum
 */
class Step5OperatorsSumTests {
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

}