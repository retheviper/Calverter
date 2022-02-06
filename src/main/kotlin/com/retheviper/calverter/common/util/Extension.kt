package com.retheviper.calverter.common.util

import kotlin.math.round

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

/**
 * If string contains full-width character, convert it to half-width.
 */
fun String.toHalfWidthNumber(): String {
    return map {
        when (it) {
            '１' -> '1'
            '２' -> '2'
            '３' -> '3'
            '４' -> '4'
            '５' -> '5'
            '６' -> '6'
            '７' -> '7'
            '８' -> '8'
            '９' -> '9'
            '０' -> '0'
            else -> it
        }
    }.joinToString(separator = "")
}