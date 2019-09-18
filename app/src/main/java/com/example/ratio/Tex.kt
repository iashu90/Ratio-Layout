package ui.trident.ratiotest

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.Display
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.min
import kotlin.math.round


class Tex : AppCompatTextView {

    var screenWidth = 0
    var screenHeight = 0
    var baseHeight =
        1920.0 // As per the zeplin design 360x640dp == 1080x1920px == 1776 ( after removing bottom navigation )

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)


    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        init(context)
    }

    fun init(context: Context) {
        val vm: WindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = vm.defaultDisplay
        val size = Point()
        // Taken Full size of the screen : Need to change only portion of screen if required.
        display.getRealSize(size)
        screenWidth = size.x
        screenHeight = size.y

    }

    var oldHeight: Int = 0
//    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
//        var newHeight: Int = ((((screenHeight / baseHeight)) * heightSize)).toInt()
//        if (oldHeight > 0) {
//            newHeight = min(oldHeight, newHeight)
//        }
//        oldHeight = newHeight
//
//
//        super.onMeasure(widthMeasureSpec, newHeight)
//        val size = View.MeasureSpec.getSize(widthMeasureSpec)
//        setMeasuredDimension(size, newHeight)
//    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        Log.v("[View name] onMeasure w", MeasureSpec.toString(widthMeasureSpec))
        Log.v("[View name] onMeasure h", MeasureSpec.toString(heightMeasureSpec))

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = measuredWidth + paddingLeft + paddingRight
        val desiredHeight = measuredHeight + paddingTop + paddingBottom

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        heightSize =
            (((heightSize / Resources.getSystem().displayMetrics.density)) * round(Resources.getSystem().displayMetrics.density)).toInt()


        var newHeight: Int
        newHeight = if (screenHeight > baseHeight)
            (((screenHeight / baseHeight) * heightSize)).toInt()
        else
            heightSize


        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            //    width = Math.min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            //   width = desiredWidth
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            if (oldHeight > 0) {
                newHeight = min(oldHeight, newHeight)
            }
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            newHeight = min(desiredHeight, heightSize)

        } else {
            //Be whatever you want
            newHeight = desiredHeight
        }
//        // var sample =  pxToDp(height)

//
//        //MUST CALL THIS
//        setMeasuredDimension(0, newHeight)


        oldHeight = newHeight
        setMeasuredDimension(desiredWidth, newHeight)

//        setMeasuredDimension(
//            measureDimension(desiredWidth, widthMeasureSpec),
//            measureDimension(newHeight, heightMeasureSpec)
//        )

    }


    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }

        if (result < desiredSize) {
            Log.e("ChartView", "The view is too small, the content might get cut")
        }
        return result
    }


    fun pxToDp(px: Int): Float {
        return (px / Resources.getSystem().displayMetrics.density)
    }

    fun dpToPx(dp: Int): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }

}