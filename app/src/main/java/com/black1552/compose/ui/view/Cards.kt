package com.ybn.purchasecompose.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCard(modifier: Modifier = Modifier,content:@Composable ()->Unit) {
    Card(
        modifier = modifier.then(
            Modifier.border(
                1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
        ),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        content()
    }
}