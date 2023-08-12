package com.ybn.purchasecompose.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DefaultCheckBox(
    check: Boolean,
    text: String,
    onCheck: (Boolean) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Checkbox(checked = check, onCheckedChange = onCheck)
            Text(text = text)
        }
    )
}