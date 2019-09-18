package com.example.ratio

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class AbbottImageView : AppCompatImageView {

    private val abbottPercentage = AbbottPercentage()

    constructor(context: Context) : super(context) {
        abbottPercentage.doCalculate(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        abbottPercentage.init(context, attrs, this)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        abbottPercentage.init(context, attrs, this)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            abbottPercentage.measureWidth(widthMeasureSpec),
            abbottPercentage.measureHeight(heightMeasureSpec)
        )
        abbottPercentage.setMargins(this)
        abbottPercentage.setPadding(this)
    }



}