package com.stonebell.lottoman.dagger2

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy

class MokitoTest {
//    @Test
//    fun test() = runBlocking<Unit> {
//        val dao = mock(Dao::class.java)
//        val repo = spy(Repo(dao))
//        val viewModel = ViewModel(repo)
//
//        Single.just<String>("hello").map { 10 }.subscribe { t1, t2 ->
//            System.out.println("test : $t1")
//
//        }
//
//        System.out.println("step1")
//        Mockito.`when`(dao.getAll()).thenReturn(Single.just<List<String>>(emptyList()).doOnSuccess {  System.out.println("list!.${it}") })
////        System.out.println("step2 : " + dao.getAll().subscribe( { System.out.println("step2 subscribe.${it}") }, {}))
//        Mockito.`when`(repo.isEmpty()).thenReturn(Single.just(false).doOnSuccess { System.out.println("test.${it}") })
//        System.out.println("step3")
//
//        viewModel.checkUser()
//        delay(1000)
//    }

    @Test
    fun test2() {
        val dao = mock(Dao::class.java)
        val repo = spy(Repo(dao))
        val viewModel = ViewModel(repo)

        Single.just<String>("hello").map { 10 }.subscribe { t1, t2 ->
            System.out.println("test : $t1")

        }


        System.out.println("step1")
        val mock = Single.just<MutableList<String>>(mutableListOf("aaa"))
        Mockito.`when`(dao.getAll()).thenReturn(mock)
//        System.out.println("step2 : " + dao.getAll().subscribe( { System.out.println("step2 subscribe.${it}") }, {}))
//        val changeEmpty = Single.just(false).doOnSuccess { System.out.println("test.${it}") }
        val mock2 = Single.just<MutableList<String>>(mutableListOf("bbb"))
        Mockito.`when`(repo.isEmpty()).thenReturn(Single.just<Boolean>(false))
        Mockito.doReturn(Single.just<Boolean>(false)).`when`(repo).isEmpty()
        System.out.println("step3")

        viewModel.checkUser()

    }
}

class ViewModel(val repo: Repo) {
    fun checkUser() {
        repo.isEmpty()
                .subscribe ({

                    System.out.println("subscribe.${it}")
                },{
                    System.out.println("subscribe error ${it}")
                })
    }
}

open class Repo(val dao: Dao) {
    open fun isEmpty(): Single<Boolean>{
        return dao.getAll().map {
            true
        }
    }
}

interface Dao{
    fun getAll(): Single<MutableList<String>>
}