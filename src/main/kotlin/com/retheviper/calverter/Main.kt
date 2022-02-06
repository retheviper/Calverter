package com.retheviper.calverter// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.retheviper.calverter.view.page.ConverterPage

@Composable
@Preview
fun App() {
    MaterialTheme {
        ConverterPage()
    }
}

fun main() = application {
    Window(
        title = "calverter",
        onCloseRequest = ::exitApplication,
        resizable = false
    ) {
        App()
    }
}
