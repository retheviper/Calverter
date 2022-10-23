package com.retheviper.calverter.common.constant


interface Units

enum class Area(val value: String) : Units {
    PYOUNG("坪"),
    SQUARE_METER("㎡");

    companion object {
        private val map = values().associateBy(Area::value)
        fun fromValue(value: String): Area = map.getValue(value)
        val items = values().map { it.value }
    }
}

enum class Length(val value: String) : Units {
    METER("m"),
    KILOMETER("km"),
    MILE("mi");

    companion object {
        private val map = values().associateBy(Length::value)
        fun fromValue(value: String): Length = map.getValue(value)
        val items = values().map { it.value }
    }
}

enum class Volume(val value: String) : Units {
    LITER("ℓ"),
    CUBIC_METER("㎥");

    companion object {
        private val map = values().associateBy(Volume::value)
        fun fromValue(value: String): Volume = map.getValue(value)
        val items = values().map { it.value }
    }
}