package com.stonebell.lottoman

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrcodeScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
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
        Log.d("handleResult toString ", rawResult.toString())

        val sourceResult = "http://qr.645lotto.net/?v=0809m021132353640m071523344143q132324323538q040714213942q0405131941450000000726"

        val parseResult = sourceResult.substringAfter("?v=")

        Log.d("handleResult ", parseResult.substring(IntRange(0, 4)));


        onBackPressed()
    }
}