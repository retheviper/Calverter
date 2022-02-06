package com.retheviper.calverter.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.retheviper.calverter.common.constant.Area
import com.retheviper.calverter.common.constant.ConvertType
import com.retheviper.calverter.common.constant.Length
import com.retheviper.calverter.common.constant.Volume
import com.retheviper.calverter.convert.AreaConverter
import com.retheviper.calverter.convert.LengthConverter
import com.retheviper.calverter.convert.VolumeConverter
import com.retheviper.calverter.view.component.ConvertInput
import com.retheviper.calverter.view.component.ConvertResult
import com.retheviper.calverter.view.component.CustomDropdownMenu

@Composable
fun ConverterPage() {
    Column {
        ConverterRow(ConvertType.AREA)
        ConverterRow(ConvertType.LENGTH)
        ConverterRow(ConvertType.VOLUME)
    }
}

@Composable
fun ConverterRow(convertType: ConvertType) {
    Card {
        var input by remember { mutableStateOf("0.0") }
        var result by remember { mutableStateOf("0.0") }
        var inputDropdownIndex by remember { mutableStateOf(0) }
        var resultDropdownIndex by remember { mutableStateOf(0) }

        val (dropdownItems, resultAtDropdownSelect, title) = when (convertType) {
            ConvertType.AREA -> {
                Triple(
                    first = Area.items,
                    second = {
                        AreaConverter.convert(
                            from = Area.values()[inputDropdownIndex],
                            to = Area.fromValue(Area.items.filterIndexed { index, _ -> index != inputDropdownIndex }[resultDropdownIndex]),
                            input = input
                        )
                    },
                    third = "Area"
                )
            }
            ConvertType.LENGTH -> {
                Triple(
                    first = Length.items,
                    second = {
                        LengthConverter.convert(
                            from = Length.values()[inputDropdownIndex],
                            to = Length.fromValue(Length.items.filterIndexed { index, _ -> index != inputDropdownIndex }[resultDropdownIndex]),
                            input = input
                        )
                    },
                    third = "Length"
                )
            }
            ConvertType.VOLUME -> {
                Triple(
                    first = Volume.items,
                    second = {
                        VolumeConverter.convert(
                            from = Volume.values()[inputDropdownIndex],
                            to = Volume.fromValue(Area.items.filterIndexed { index, _ -> index != inputDropdownIndex }[resultDropdownIndex]),
                            input = input
                        )
                    },
                    third = "Volume"
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
            ) {
                Text(title)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Column {
                        CustomDropdownMenu(
                            index = inputDropdownIndex,
                            items = dropdownItems,
                            onClick = {
                                inputDropdownIndex = it
                                result = resultAtDropdownSelect()
                            }
                        )
                        ConvertInput(
                            value = input,
                            onValueChange = {
                                input = it
                                result = resultAtDropdownSelect()
                            }
                        )
                    }
                    Column {
                        CustomDropdownMenu(
                            index = resultDropdownIndex,
                            items = dropdownItems.filterIndexed { index, _ -> index != inputDropdownIndex },
                            onClick = { resultDropdownIndex = it }
                        )
                        ConvertResult(result)
                    }
                }
            }
        }
    }
}