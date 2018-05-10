package com.stonebell.lottoman.gson

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class GsonUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun gsonDefault(){
        val jsonString = "{\"id\":6}"
        val jsonClass = Gson().fromJson(jsonString, GsonTest::class.java)

        println("jsonClass !!! id : ${jsonClass.id}")

        val  jsonTestClass = Gson().fromJson(jsonString, GsonTestClass::class.java)

        println("jsonClass !!! id : ${jsonTestClass.realId}")
    }

    class GsonTestClass(@SerializedName("id") val realId: Long)

    data class GsonTest(val id: Long)

}