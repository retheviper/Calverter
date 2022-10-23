package com.retheviper.calverter.convert

import com.retheviper.calverter.common.constant.Area
import com.retheviper.calverter.common.constant.Length
import com.retheviper.calverter.common.constant.Units
import com.retheviper.calverter.common.constant.Volume
import com.retheviper.calverter.common.util.toHalfWidthNumber
import java.math.BigDecimal
import java.math.RoundingMode

object Converter {

    const val CANNOT_CONVERT = "Cannot convert"

    fun convert(
        from: Units,
        to: Units,
        input: String,
        round: Int = 3,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): String {
        if (input.isBlank()) return "input is blank"
        val value = input.toHalfWidthNumber().toBigDecimalOrNull() ?: return CANNOT_CONVERT

        return when {
            from is Area && to is Area -> convertArea(from, to, value, round, roundingMode)
            from is Length && to is Length -> convertLength(from, to, value, round, roundingMode)
            from is Volume && to is Volume -> convertVolume(from, to, value, round, roundingMode)
            else -> throw IllegalArgumentException(CANNOT_CONVERT)
        }
    }

    private fun convertArea(
        from: Area,
        to: Area,
        value: BigDecimal,
        round: Int,
        roundingMode: RoundingMode
    ): String {
        return when (from to to) {
            Area.PYOUNG to Area.SQUARE_METER -> value.timesWith(BigDecimal(3.305785), round, roundingMode)
            Area.SQUARE_METER to Area.PYOUNG -> value.timesWith(BigDecimal(0.3025), round, roundingMode)
            else -> BigDecimal.ZERO
        }.toPlainString()
    }

    private fun convertLength(
        from: Length,
        to: Length,
        value: BigDecimal,
        round: Int,
        roundingMode: RoundingMode
    ): String {
        return when (from to to) {
            Length.METER to Length.KILOMETER -> value.timesWith(BigDecimal(0.001), round, roundingMode)
            Length.METER to Length.MILE -> value.timesWith(BigDecimal(0.001609344), round, roundingMode)
            Length.MILE to Length.KILOMETER -> value.timesWith(BigDecimal(1.609344), round, roundingMode)
            Length.MILE to Length.METER -> value.timesWith(BigDecimal(1609.344), round, roundingMode)
            Length.KILOMETER to Length.METER -> value.timesWith(BigDecimal(1000), round, roundingMode)
            Length.KILOMETER to Length.MILE -> value.timesWith(BigDecimal(0.6214), round, roundingMode)
            else -> BigDecimal.ZERO
        }.toPlainString()
    }

    private fun convertVolume(
        from: Volume,
        to: Volume,
        value: BigDecimal,
        round: Int,
        roundingMode: RoundingMode
    ): String {
        return when (from to to) {
            Volume.LITER to Volume.CUBIC_METER -> value.timesWith(BigDecimal(0.001), round, roundingMode)
            Volume.CUBIC_METER to Volume.LITER -> value.timesWith(BigDecimal(1000), round, roundingMode)
            else -> BigDecimal.ZERO
        }.toPlainString()
    }

    private fun BigDecimal.timesWith(other: BigDecimal, round: Int, roundingMode: RoundingMode): BigDecimal =
        (this.setScale(round, roundingMode) * other.setScale(round, roundingMode)).setScale(round, roundingMode)
}