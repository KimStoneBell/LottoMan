package com.stonebell.lottoman.retrofit
import android.database.Observable
import com.google.gson.annotations.SerializedName
import com.stonebell.lottoman.data.datasource.web.LottoInfoApi
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class Retrofit2UnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun retrofitDefault(){
        val page = "https://api.github.com/users/KimStoneBell"
        val jsonString = ""

        val retrofit = initRetrofit();

        val githubUserApi = retrofit.create(GithubUserApi::class.java)

        val reponse = githubUserApi.getGithubUser("KimStoneBell").execute()

        val reponseData = reponse.body()

        println("jsonClass !!! id : ${reponseData.toString()}")
    }

    @Test
    fun retrofitObservable(){
        val testObserver = TestObserver.create<LottoInfoApi.Companion.LottoWebData>()
        LottoInfoApi.create().getLottoInfoRx(10.toString())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    println("print next : $it")
                }
                .subscribe (testObserver)


        testObserver.awaitTerminalEvent()
    }

    fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()

    }

    interface GithubUserApi{
        @GET("users/{username}")
        fun getGithubUserRx(@Path("username") username: String): Observable<GithubUser>

        @GET("users/{username}")
        fun getGithubUser(@Path("username") username: String): Call<GithubUser>

        data class GithubUser(
                @SerializedName("login") val nickName: String,
                @SerializedName("id") val id: String,
                @SerializedName("avatar_url") val avatarUrl: String,
                @SerializedName("url") val pageUrl: String
        )
    }

}