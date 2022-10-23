package com.retheviper.calverter.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.retheviper.calverter.common.constant.Area
import com.retheviper.calverter.common.constant.ConvertType
import com.retheviper.calverter.common.constant.Length
import com.retheviper.calverter.common.constant.Volume
import com.retheviper.calverter.convert.Converter
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

        val (dropdownItems, resultAtDropdownSelect) = getRowContent(
            convertType,
            inputDropdownIndex,
            resultDropdownIndex
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Text(convertType.toString())
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Column {
                        CustomDropdownMenu(
                            selectedIndex = inputDropdownIndex,
                            items = dropdownItems
                        ) {
                            inputDropdownIndex = it
                            result = resultAtDropdownSelect(input)
                        }
                        ConvertInput(
                            value = input
                        ) {
                            input = it
                            result = resultAtDropdownSelect(input)
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        CustomDropdownMenu(
                            selectedIndex = resultDropdownIndex,
                            items = dropdownItems.filterIndexed { index, _ -> index != inputDropdownIndex }
                        ) {
                            resultDropdownIndex = it
                        }
                        ConvertResult(result)
                    }
                }
            }
        }
    }
}

private fun getRowContent(
    convertType: ConvertType,
    inputDropdownIndex: Int,
    resultDropdownIndex: Int
) = when (convertType) {
    ConvertType.AREA -> {
        Pair(
            first = Area.items,
            second = { input: String ->
                Converter.convert(
                    from = Area.values()[inputDropdownIndex],
                    to = Area.fromValue(Area.items.filterIndexed { index, _ -> index != inputDropdownIndex }[resultDropdownIndex]),
                    input = input
                )
            }
        )
    }

    ConvertType.LENGTH -> {
        Pair(
            first = Length.items,
            second = { input: String ->
                Converter.convert(
                    from = Length.values()[inputDropdownIndex],
                    to = Length.fromValue(Length.items.filterIndexed { index, _ -> index != inputDropdownIndex }[resultDropdownIndex]),
                    input = input
                )
            }
        )
    }

    ConvertType.VOLUME -> {
        Pair(
            first = Volume.items,
            second = { input: String ->
                Converter.convert(
                    from = Volume.values()[inputDropdownIndex],
                    to = Volume.fromValue(Volume.items.filterIndexed { index, _ -> index != inputDropdownIndex }[resultDropdownIndex]),
                    input = input
                )
            }
        )
    }
}