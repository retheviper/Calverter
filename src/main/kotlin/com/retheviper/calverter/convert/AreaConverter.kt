package com.retheviper.calverter.convert

import com.retheviper.calverter.common.constant.Area
import com.retheviper.calverter.common.util.toHalfWidthNumber
import java.math.BigDecimal
import java.math.RoundingMode

object AreaConverter {

    fun convert(
        from: Area,
        to: Area,
        input: String,
        round: Int = 3,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): String {
        if (input.isBlank()) return "Input number"
        val value = input.toHalfWidthNumber().toBigDecimalOrNull() ?: return "Cannot convert"

        val result = when (from to to) {
            Area.PYOUNG to Area.SQUARE_METER -> (value * BigDecimal(3.305785)).setScale(round, roundingMode)
            Area.SQUARE_METER to Area.PYOUNG -> (value * BigDecimal(0.3025)).setScale(round, roundingMode)
            else -> BigDecimal(0.0)
        }

        return result.toPlainString()
    }
}