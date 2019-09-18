package com.example.ratio

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import ui.trident.ratiotest.Utils

class AbbottPercentage {


    var screenWidth = 0
    var screenHeight = 0
    var baseHeight =
        640.0 // As per the zeplin design 360x640dp == 1080x1920px == 1776 ( after removing bottom navigation )
    var baseWidth =
        360.0 // As per the zeplin design 360x640dp == 1080x1920px == 1776 ( after removing bottom navigation )
    var customHeight = 1
    var customWidth = 1
    var calculatedHeightRatio: Double = 0.0
    var calculatedWidthRatio: Double = 0.0

    // Margins
    var abbotMargin = 0
    var abbotMarginLeft = 0
    var abbotMarginTop = 0
    var abbotMarginRight = 0
    var abbotMarginBottom = 0

    // Padding
    var abbotPadding = 0
    var abbotPaddingLeft = 0
    var abbotPaddingTop = 0
    var abbotPaddingRight = 0
    var abbotPaddingBottom = 0

    // Text Properties
    var abbott_textSize = 0f

    lateinit var context: Context
    fun init(
        context: Context,
        attrs: AttributeSet,
        view: View
    ) {
        this.context = context
        initalize(context, attrs, view)

    }

    private fun initalize(context: Context, attrs: AttributeSet, view: View) {
        val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.AbbottTextView, 0, 0)
        customHeight = ta.getInt(R.styleable.AbbottTextView_abbott_height, 1)
        customWidth = ta.getInt(R.styleable.AbbottTextView_abbott_width, 1)

        // Margins
        abbotMargin = ta.getInt(R.styleable.AbbottTextView_abbott_margin, 0)
        if (abbotMargin == 0) {
            abbotMarginLeft = ta.getInt(R.styleable.AbbottTextView_abbott_marginLeft, 0)
            abbotMarginTop = ta.getInt(R.styleable.AbbottTextView_abbott_marginTop, 0)
            abbotMarginRight = ta.getInt(R.styleable.AbbottTextView_abbott_marginRight, 0)
            abbotMarginBottom = ta.getInt(R.styleable.AbbottTextView_abbott_marginBottom, 0)
        } else {
            abbotMarginLeft = abbotMargin
            abbotMarginTop = abbotMargin
            abbotMarginRight = abbotMargin
            abbotMarginBottom = abbotMargin
        }

        // Padding
        abbotPadding = ta.getInt(R.styleable.AbbottTextView_abbott_padding, 0)
        if (abbotPadding == 0) {
            abbotPaddingLeft = ta.getInt(R.styleable.AbbottTextView_abbott_paddingLeft, 0)
            abbotPaddingTop = ta.getInt(R.styleable.AbbottTextView_abbott_paddingTop, 0)
            abbotPaddingRight = ta.getInt(R.styleable.AbbottTextView_abbott_paddingRight, 0)
            abbotPaddingBottom = ta.getInt(R.styleable.AbbottTextView_abbott_paddingBottom, 0)
        } else {
            abbotPaddingLeft = abbotPadding
            abbotPaddingTop = abbotPadding
            abbotPaddingRight = abbotPadding
            abbotPaddingBottom = abbotPadding
        }

        abbott_textSize = ta.getFloat(R.styleable.AbbottTextView_abbott_textSize, -1f)

        ta.recycle()

        setViewProperties(view)
        doCalculate(context)
    }

    private fun setViewProperties(view: View) {
        when (view) {
            is TextView -> if (abbott_textSize > -1) {
                view.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    ((abbott_textSize / baseWidth) * Utils.getSmallestWidth(context)).toFloat()
                )
            }
        }
    }


    fun doCalculate(context: Context) {
        val vm: WindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = vm.defaultDisplay
        val size = Point()
        // Taken Full size of the screen : Need to change only portion of screen if required.
        display.getRealSize(size)
        screenWidth = size.x
        screenHeight = size.y

        // calculatedHeightRatio =  customHeight / (baseHeight- pxToDp(getStatusBarHeight(context) + getNavBarHeight(context)))
        calculatedHeightRatio = customHeight / baseHeight
        calculatedWidthRatio = customWidth / baseWidth


    }

    fun setMargins(view: View) {
        val margins = ViewGroup.MarginLayoutParams::class.java.cast(view.layoutParams)
        val marginsValue = getMargins()

        margins?.leftMargin = marginsValue.left
        margins?.topMargin = marginsValue.top
        margins?.rightMargin = marginsValue.right
        margins?.bottomMargin = marginsValue.bottom
        view.layoutParams = margins
    }


    fun setPadding(view: View) {
        val paddingValue = getPadding()
        view.setPadding(
            paddingValue.left,
            paddingValue.top,
            paddingValue.right,
            paddingValue.bottom
        )

    }


    fun getMargins(): Rect {
        val lMargin = ((abbotMarginLeft / baseWidth) * screenWidth).toInt()
        val tMargin = ((abbotMarginTop / baseHeight) * screenHeight).toInt()
        val rMargin = ((abbotMarginRight / baseWidth) * screenWidth).toInt()
        val bMargin = ((abbotMarginBottom / baseHeight) * screenHeight).toInt()
        return Rect(lMargin, tMargin, rMargin, bMargin)
    }

    fun getPadding(): Rect {
        val leftPadding = ((abbotPaddingLeft / baseWidth) * screenWidth).toInt()
        val topPadding = ((abbotPaddingTop / baseHeight) * screenHeight).toInt()
        val rightPadding = ((abbotPaddingRight / baseWidth) * screenWidth).toInt()
        val bottomPadding = ((abbotPaddingBottom / baseHeight) * screenHeight).toInt()
        return Rect(leftPadding, topPadding, rightPadding, bottomPadding)
    }

    fun measureHeight(measureSpec: Int): Int {
        val newHeightSize = (calculatedHeightRatio * screenHeight).toInt()
        return View.resolveSizeAndState(newHeightSize, measureSpec, 0)
    }

    fun measureWidth(measureSpec: Int): Int {
        val newWidthSize = (calculatedWidthRatio * screenWidth).toInt()
        return View.resolveSizeAndState(newWidthSize, measureSpec, 0)
    }

}