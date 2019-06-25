package com.stonebell.lottoman.presentation.lotto.make

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stonebell.lottoman.data.repository.LottoDataRepository
import com.stonebell.lottoman.domain.ILottoRepository
import com.stonebell.lottoman.data.datasource.web.LottoInfoApi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class LottoDBMakeViewModel : ViewModel(){

    private val database: ILottoRepository by lazy { LottoDataRepository() }

    private val disposes = CompositeDisposable()

    val startNumText = MutableLiveData<String>()
    val endNumText = MutableLiveData<String>()

    val viewText = MutableLiveData<String>()

    fun updateDataRequest(){
        if(!startNumText.value.isNullOrEmpty() && !endNumText.value.isNullOrEmpty()){
            disposes += callLottoInfo(startNumText.value!!.toInt(), endNumText.value!!.toInt())
                    .map { it.convertToLottData() }
                    .subscribe {
                        database.addLottoData(it)
                        viewText.postValue(it.toString())
                        Log.d("hyuhyu", it.toString())
                    }
        }
    }


    private fun callLottoInfo(startNo: Int, endNo: Int): Observable<LottoInfoApi.Companion.LottoWebData> {
        val infoRange = (startNo..endNo)
        return Observable.fromIterable(infoRange)
                .flatMap { callLottoInfo(it) }
    }

    private fun callLottoInfo(number: Int): Observable<LottoInfoApi.Companion.LottoWebData> {
        return LottoInfoApi.create().getLottoInfoRx(number.toString())
    }

    override fun onCleared() {
        super.onCleared()
        disposes.clear()
    }

}