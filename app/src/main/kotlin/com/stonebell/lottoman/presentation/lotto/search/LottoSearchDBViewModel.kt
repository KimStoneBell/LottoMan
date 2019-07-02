package com.stonebell.lottoman.presentation.lotto.search

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stonebell.lottoman.di.component.DaggerRepository
import com.stonebell.lottoman.domain.ILottoRepository
import io.reactivex.processors.PublishProcessor

class LottoSearchDBViewModel : ViewModel(){

    val database: ILottoRepository by lazy { DaggerRepository.create().lottoDataRepo() }

    private val searchSubject = PublishProcessor.create<Int>()

    val targetLottoIndex = MutableLiveData<String>()

    val lottoInfoText = bindLottoSearchRepo()

    fun searchLottoInfo(){

        if(!TextUtils.isEmpty(targetLottoIndex.value)){
            searchSubject.onNext(targetLottoIndex.value!!.toInt())
        }
    }

    fun searchLottoLastNumber(){
        searchSubject.onNext(ILottoRepository.GAME_ROUND_LAST)
    }

    private fun bindLottoSearchRepo() : LiveData<String> {

        val process = searchSubject.flatMap { database.getLottoData((it)).toFlowable() }
                                   .map { it.balls.toString() }

        return LiveDataReactiveStreams.fromPublisher(process)
    }

}