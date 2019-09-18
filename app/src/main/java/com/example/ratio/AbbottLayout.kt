package ui.trident.ratiotest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.max

class AbbottLayout : ConstraintLayout {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var parentDesireWidth = 0
        var parentDesireHeight = 0

        if (childCount > 0) {

            for (i in 0 until childCount) {
                val childAt = getChildAt(i)

                val layoutParams = childAt.layoutParams as MarginLayoutParams

                measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, 0)

                parentDesireWidth += (childAt.measuredWidth
                        + layoutParams.leftMargin
                        + layoutParams.rightMargin)
                parentDesireHeight += (childAt.measuredHeight
                        + layoutParams.topMargin
                        + layoutParams.bottomMargin)
            }

            parentDesireWidth += paddingLeft + paddingRight
            parentDesireHeight += paddingTop + paddingBottom

            parentDesireWidth = max(parentDesireWidth, suggestedMinimumWidth)
            parentDesireHeight = max(parentDesireHeight, suggestedMinimumHeight)

            setMeasuredDimension(
                View.resolveSize(parentDesireWidth, widthMeasureSpec),
                View.resolveSize(parentDesireHeight, heightMeasureSpec)
            )
        }
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val parentPaddingTop = paddingTop
        val parentPaddingLeft = paddingLeft

        if (childCount > 0) {

            var multiHeight = 0

            for (i in 0 until childCount) {
                val child = getChildAt(i)

                val childLayoutParams = child.layoutParams as MarginLayoutParams

                child.layout(
                    parentPaddingLeft + childLayoutParams.leftMargin,
                    multiHeight + parentPaddingTop + childLayoutParams.topMargin,
                    child.measuredWidth + parentPaddingLeft + childLayoutParams.leftMargin,
                    child.measuredHeight + multiHeight + parentPaddingTop + childLayoutParams.topMargin
                )

                multiHeight += (child.measuredHeight
                        + childLayoutParams.topMargin
                        + childLayoutParams.bottomMargin)
            }
        }
    }





}
