package com.retheviper.calverter.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun ConvertInput(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("input") },
        singleLine = true
    )
}

@Composable
fun ConvertResult(value: String) {
    TextField(
        value = value,
        onValueChange = {},
        label = { Text("result") },
        readOnly = true,
        singleLine = true
    )
}

@Composable
fun CustomDropdownMenu(
    index: Int,
    items: List<String>,
    onClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Text(
        text = items[index],
        modifier = Modifier.clickable { expanded = true },
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        items.forEachIndexed { index, value ->
            DropdownMenuItem(
                onClick = {
                    onClick(index)
                    expanded = false
                },
                enabled = true,
            ) {
                Text(value)
            }
        }
    }
}