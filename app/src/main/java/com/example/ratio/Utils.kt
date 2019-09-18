package ui.trident.ratiotest

import android.content.Context
import android.content.res.Resources
import android.os.Build
import kotlin.math.ceil

class Utils {

    companion object {
        fun getStatusBarHeight(context: Context): Int {
            val resources = context.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return if (resourceId > 0)
                resources.getDimensionPixelSize(resourceId)
            else
                ceil(((if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 24 else 25) * resources.displayMetrics.density).toDouble()).toInt()
        }

        fun getNavBarHeight(c: Context): Int {
            val resourceId = c.resources
                .getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId > 0) {
                c.resources.getDimensionPixelSize(resourceId)
            } else 0
        }

        fun pxToDp(px: Int): Float {
            return (px / Resources.getSystem().displayMetrics.density)
        }

        fun dpToPx(dp: Int): Float {
            return (dp * Resources.getSystem().displayMetrics.density)
        }

        fun getSmallestWidth(context: Context): Int =
            context.resources.configuration.smallestScreenWidthDp
    }

}