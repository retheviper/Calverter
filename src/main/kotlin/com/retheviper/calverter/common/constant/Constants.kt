package com.retheviper.calverter.common.constant

import javax.sound.sampled.FloatControl.Type.VOLUME
import javax.swing.text.html.HTML.Tag.AREA

enum class ConvertType {
    AREA,
    LENGTH,
    VOLUME
}

enum class AssociatedAnimalKr(val korean: String, val hanja: String) {
    MONKEY("원숭이", "申"),
    ROOSTER("닭", "酉"),
    DOG("개", "戌"),
    PIG("돼지", "亥"),
    RAT("쥐", "子"),
    OX("소", "丑"),
    TIGER("호랑이", "寅"),
    RABBIT("토끼", "卯"),
    DRAGON("용", "辰"),
    SNAKE("뱀", "巳"),
    HORSE("말", "午"),
    SHEEP("양", "未")
}