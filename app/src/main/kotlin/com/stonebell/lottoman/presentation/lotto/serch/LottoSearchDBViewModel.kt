package com.stonebell.lottoman.presentation.lotto.serch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stonebell.lottoman.data.repository.LottoDataRepository
import com.stonebell.lottoman.domain.ILottoRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class LottoSearchDBViewModel : ViewModel(){

    private val database: ILottoRepository by lazy { LottoDataRepository() }

    val lottoInfoText = MutableLiveData<String>()
    val targetLottoText = MutableLiveData<String>()

    private val disposes = CompositeDisposable()

    fun searchLottoInfo(){
        targetLottoText.value?.let{
            disposes += database.getLottoData(it.toInt())
                    .map {it.balls.toString()}
                    .subscribe ({lottoInfoText.postValue(it)},{})
        }
    }

    fun searchLottoLastNumber(){
        disposes += database.getLottoData(ILottoRepository.GAME_ROUND_LAST)
                .map { "Last Lotto No. : $it"}
                .subscribe ({lottoInfoText.postValue(it)},{})
    }

    override fun onCleared() {
        super.onCleared()
        disposes.clear()
    }

}