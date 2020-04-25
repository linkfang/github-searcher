package amber.link

/*
* Created by Amber Yiyao Zhou & Link Zhou Fang on December 02, 2019
*/

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(min: Int, max: Int) : InputFilter {
    // region properties
    private var min: Int = 0
    private var max: Int = 0
    // endregion

    init {
        this.min = min
        this.max = max
    }
    // region method
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        destination: Spanned,
        destinationStart: Int,
        destinationEnd: Int
    ): CharSequence? {
        try {
            val input = (destination.subSequence(0, destinationStart).toString() + source +
                    destination.subSequence(destinationEnd, destination.length)).toInt()
            if (isInRange(min, max, input)) {
                return null
            }
        } catch (ex: NumberFormatException) {
            // just ignore the exception
        }
        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c in a..b else c in b..a
    }

    // endregion
}