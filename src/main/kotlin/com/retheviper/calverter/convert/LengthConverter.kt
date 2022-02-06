package com.retheviper.calverter.convert

import com.retheviper.calverter.common.constant.Length
import com.retheviper.calverter.common.util.toHalfWidthNumber
import java.math.BigDecimal
import java.math.RoundingMode

object LengthConverter {

    fun convert(
        from: Length,
        to: Length,
        input: String,
        round: Int = 3,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): String {
        if (input.isBlank()) return "Input number"
        val value = input.toHalfWidthNumber().toBigDecimalOrNull() ?: return "Cannot convert"

        val result = when (from to to) {
            Length.METER to Length.KILOMETER -> (value / BigDecimal(1000)).setScale(round, roundingMode)
            Length.METER to Length.MILE -> (value * BigDecimal(0.001609344)).setScale(round, roundingMode)
            Length.MILE to Length.KILOMETER -> (value * BigDecimal(1.609344)).setScale(round, roundingMode)
            Length.MILE to Length.METER -> (value * BigDecimal(1609.344)).setScale(round, roundingMode)
            Length.KILOMETER to Length.METER -> (value * BigDecimal(1000)).setScale(round, roundingMode)
            Length.KILOMETER to Length.MILE -> (value * BigDecimal(0.6214)).setScale(round, roundingMode)
            else -> BigDecimal(0.0)
        }

        return result.toPlainString()
    }
}