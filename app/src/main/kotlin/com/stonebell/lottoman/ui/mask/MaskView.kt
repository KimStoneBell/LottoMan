package com.stonebell.lottoman.ui.mask

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.*


class MaskView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr), View.OnTouchListener {
    private val bmPaint = Paint()
    private val drawPaint = Paint()
    private val path = Path()
    private var cloneCanvas: Canvas? = null
    private var bitmap: Bitmap? = null
    private var firstTimeThru: Boolean = true
    private val steps = 20
    private val threshold = 0.70

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setOnTouchListener(this)
    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.let {
            if (firstTimeThru) {
                firstTimeThru = false

                it.drawColor(Color.BLACK)

                bitmap = Bitmap.createBitmap(it.width, it.height, Bitmap.Config.ARGB_8888)
                cloneCanvas = Canvas()
                cloneCanvas?.setBitmap(bitmap)
                cloneCanvas?.drawColor(Color.BLACK)

                drawPaint.style = Paint.Style.STROKE
                drawPaint.strokeWidth = it.width / 15f

                drawPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }

            cloneCanvas?.drawPath(path, drawPaint)
            it.drawBitmap(bitmap, 0f, 0f, bmPaint)
        }

        super.onDraw(canvas)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val xPos = event?.x!!.toFloat()
        val yPos = event?.y!!.toFloat()

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                path.moveTo(xPos, yPos)
                return true
            }

            MotionEvent.ACTION_MOVE -> path.lineTo(xPos, yPos)

            MotionEvent.ACTION_UP -> checkProgress()

            else -> return false
        }

        invalidate()

        return true
    }

    private fun checkProgress() {

        var sum = 0
        var x = 0
        while (x < bitmap!!.width) {
            var y = 0
            while (y < bitmap!!.height) {
                if (bitmap!!.getPixel(x, y) !== Color.BLACK)
                    sum++
                y += bitmap!!.height / steps
            }
            x += bitmap!!.width / steps
        }

        val progress = sum.toDouble() / (steps * steps)

        if (progress >= threshold) visibility = GONE
    }
}