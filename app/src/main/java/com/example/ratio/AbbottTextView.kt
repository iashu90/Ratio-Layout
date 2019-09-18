package com.example.ratio

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.updatePadding
import com.example.ratio.AbbottPercentage


class AbbottTextView : AppCompatTextView {

    private val abbottPercentage = AbbottPercentage()

    constructor(context: Context) : super(context) {
        abbottPercentage.doCalculate(context)
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        abbottPercentage.init(context, attrs, this)
        val padding = abbottPercentage.getPadding()
        updatePadding(padding.left, padding.top, padding.right, padding.bottom)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        abbottPercentage.init(context, attrs, this)
    }


//    var isMeasured = false
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
////        if (!isMeasured) {
////            updateLayoutParams<ViewGroup.MarginLayoutParams> {
////                leftMargin = 200
////                rightMargin = 100
////            }
////            isMeasured = true
////        }
//
//
//    }

    //
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            abbottPercentage.measureWidth(widthMeasureSpec),
            abbottPercentage.measureHeight(heightMeasureSpec)
        )
        abbottPercentage.setMargins(this)
//        abbottPercentage.setPadding(this)

    }


}


