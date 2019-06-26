package com.stonebell.lottoman.presentation

import android.content.Context
import android.content.Intent
import com.stonebell.lottoman.presentation.lotto.scanner.QrcodeScannerActivity
import com.stonebell.lottoman.presentation.lotto.make.LottoDBMakeActivity
import com.stonebell.lottoman.presentation.lotto.nummberlist.WinningNumberListActivity
import com.stonebell.lottoman.presentation.lotto.serch.LottoSearchDBActivity
import com.stonebell.lottoman.presentation.lotto.store.StoreMapActivity

class NavigationController(val context: Context){

    fun startLottoDBMakeActivity(){
        context.startActivity(Intent(context, LottoDBMakeActivity::class.java))
    }

    fun startLottoSearchDBActivity(){
        context.startActivity(Intent(context, LottoSearchDBActivity::class.java))
    }

    fun startQrcodeScannerActivity(){
        context.startActivity(Intent(context, QrcodeScannerActivity::class.java))
    }

    fun startStoreMapActivity(){
        context.startActivity(Intent(context, StoreMapActivity::class.java))
    }

    fun startWinningListActivity(){
        context.startActivity(Intent(context, WinningNumberListActivity::class.java))
    }
}