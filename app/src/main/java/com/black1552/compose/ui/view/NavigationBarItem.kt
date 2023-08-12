package com.ybn.purchasecompose.ui.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DefaultNavItem(
    scope: RowScope,
    select: Boolean,
    onCheck: () -> Unit,
    img: ImageVector,
    text: String,
    default:Boolean = true,
    colors :NavigationBarItemColors? = if (default) null else NavigationBarItemDefaults.colors()
) {
    val navigationBarItemColor = NavigationBarItemDefaults.colors(
        selectedTextColor = Color.Blue,
        selectedIconColor = Color.Blue,
        unselectedTextColor = Color.LightGray,
        unselectedIconColor = Color.LightGray
    )
    scope.NavigationBarItem(selected = select, onClick = onCheck, icon = {
        DefaultIcon(image = img, text = text)
    }, colors = if (default) navigationBarItemColor else colors!!)
}