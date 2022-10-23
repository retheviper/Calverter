package com.retheviper.calverter.convert

import com.retheviper.calverter.common.constant.Area
import com.retheviper.calverter.common.constant.Length
import com.retheviper.calverter.common.constant.Units
import com.retheviper.calverter.common.constant.Volume
import com.retheviper.calverter.common.util.toHalfWidthNumber
import java.math.BigDecimal
import java.math.RoundingMode

object Converter {

    fun convert(
        from: Units,
        to: Units,
        input: String,
        round: Int = 3,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): String {
        if (input.isBlank()) return "input is blank"
        val value = input.toHalfWidthNumber().toBigDecimalOrNull() ?: return "Cannot convert"

        return when {
            from is Area && to is Area -> convertArea(from, to, value, round, roundingMode)

            from is Length && to is Length -> convertLength(from, to, value, round, roundingMode)

            from is Volume && to is Volume -> convertVolume(from, to, value, round, roundingMode)

            else -> throw IllegalArgumentException("Cannot convert")
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
            Area.PYOUNG to Area.SQUARE_METER -> (value * BigDecimal(3.305785)).setScale(round, roundingMode)
            Area.SQUARE_METER to Area.PYOUNG -> (value * BigDecimal(0.3025)).setScale(round, roundingMode)
            else -> BigDecimal(0.0)
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
            Length.METER to Length.KILOMETER -> (value / BigDecimal(1000)).setScale(round, roundingMode)
            Length.METER to Length.MILE -> (value * BigDecimal(0.001609344)).setScale(round, roundingMode)
            Length.MILE to Length.KILOMETER -> (value * BigDecimal(1.609344)).setScale(round, roundingMode)
            Length.MILE to Length.METER -> (value * BigDecimal(1609.344)).setScale(round, roundingMode)
            Length.KILOMETER to Length.METER -> (value * BigDecimal(1000)).setScale(round, roundingMode)
            Length.KILOMETER to Length.MILE -> (value * BigDecimal(0.6214)).setScale(round, roundingMode)
            else -> BigDecimal(0.0)
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
            Volume.LITER to Volume.CUBIC_METER -> (value * BigDecimal(1000)).setScale(round, roundingMode)
            Volume.CUBIC_METER to Volume.LITER -> (value / BigDecimal(1000)).setScale(round, roundingMode)
            else -> BigDecimal(0.0)
        }.toPlainString()
    }
}