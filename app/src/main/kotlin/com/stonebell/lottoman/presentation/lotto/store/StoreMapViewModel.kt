package com.stonebell.lottoman.presentation.lotto.store

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.stonebell.lottoman.data.repository.LottoStoreRepository
import com.stonebell.lottoman.domain.ILottoStoreRepository
import com.stonebell.lottoman.domain.entitiy.StoreData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class StoreMapViewModel : ViewModel(){

    private val disposes = CompositeDisposable()

    val storeDataText = MutableLiveData<String>()

    private val storeRepository : ILottoStoreRepository by lazy { LottoStoreRepository() }


    fun storeDataSerch(){

        disposes += storeRepository.getStoreDatas().subscribe ({
            var storeDataStr = ""

            for ((count, storeData) in it.withIndex()) {
                Log.d("johnkim", "store.storeName : $storeData")
                storeDataStr += "data $count : $storeData \n"
            }
            //TODO ????
            storeDataText.postValue(storeDataStr)

        },{})

    }

    override fun onCleared() {
        super.onCleared()
        disposes.clear()
    }
}