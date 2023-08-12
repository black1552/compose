package com.ybn.purchasecompose.ui.view

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ScrollColumn(modifier: Modifier, content: @Composable () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.then(
            Modifier.verticalScroll(scrollState)
        )
    ) {
        content()
    }
}

@Composable
fun ScrollRow(modifier: Modifier, content: @Composable () -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier.then(
            Modifier.horizontalScroll(scrollState)
        )
    ) {
        content()
    }
}