package com.retheviper.calverter.convert

import com.retheviper.calverter.common.constant.Volume
import com.retheviper.calverter.common.util.toHalfWidthNumber
import java.math.BigDecimal
import java.math.RoundingMode

object VolumeConverter {

    fun convert(
        from: Volume,
        to: Volume,
        input: String,
        round: Int = 3,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): String {
        if (input.isBlank()) return "Input number"
        val value = input.toHalfWidthNumber().toBigDecimalOrNull() ?: return "Cannot convert"

        val result = when (from to to) {
            Volume.LITER to Volume.CUBICMETER -> (value * BigDecimal(0.22702)).setScale(round, roundingMode)
            else -> BigDecimal(0.0)
        }

        return result.toPlainString()
    }
}