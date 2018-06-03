package com.stonebell.lottoman

import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrcodeScannerActivity : RxAppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var scannerView: ZXingScannerView

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        Log.d("", rawResult.text)
        onBackPressed()
    }
}