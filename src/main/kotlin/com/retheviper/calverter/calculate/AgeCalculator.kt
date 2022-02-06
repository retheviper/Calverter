package com.retheviper.calverter.calculate

import com.retheviper.calverter.common.constant.AssociatedAnimalKr
import java.time.LocalDate
import java.time.Year

object AgeCalculator {

    fun KoreanAge(birth: LocalDate, atYear: Year = Year.now()): Int {
        return atYear.value - birth.year + 1
    }

    fun associatedAnimal() {
        AssociatedAnimalKr.values()
    }
}