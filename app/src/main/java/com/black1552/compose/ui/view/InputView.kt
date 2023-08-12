package com.ybn.purchasecompose.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InputTextField(
    input: String,
    onChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    top: Dp = 0.dp,
    button: Dp = 0.dp,
    roundDp: Dp = 0.dp,
    borderWidthDp: Dp = 0.dp,
    isHidden: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(start = 25.dp, end = 25.dp, top = top, bottom = button),
    ) {
        InputFiled(
            value = input,
            onValueChange = onChange,
            placeholder = placeholder,
            drawableStart = leadingIcon,
            drawableEnd = trailingIcon,
            isHidden = isHidden,
            roundDp = roundDp,
            borderWidthDp = borderWidthDp
        )
    }
}


@Composable
fun InputFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    drawableStart: @Composable (() -> Unit)? = null,
    drawableStartPadding: Dp = 8.dp,
    drawableEnd: @Composable (() -> Unit)? = null,
    drawableEndPadding: Dp = 8.dp,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    isHidden: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    cursorBrush: Color = Color.Black,
    roundDp: Dp = 8.dp,
    borderWidthDp: Dp = 1.dp,
    alignment: Alignment = Alignment.CenterStart,
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        keyboardOptions = if (isHidden) KeyboardOptions(keyboardType = KeyboardType.Password) else keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = maxLines == 1,
        maxLines = maxLines,
        cursorBrush = SolidColor(cursorBrush),
        visualTransformation = if (isHidden) visualTransformation else VisualTransformation.None,
        decorationBox = @Composable { coreTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding((0.5).dp)
                    .border(
                        width = borderWidthDp,
                        brush = Brush.linearGradient(
                            listOf(
                                Color.LightGray,
                                Color.LightGray,
                                Color.LightGray
                            )
                        ),
                        shape = RoundedCornerShape(roundDp)
                    )
                    .padding(5.dp)

            ) {
                if (drawableStart != null) {
                    drawableStart()
                    Spacer(modifier = Modifier.width(drawableStartPadding))
                }
                Box(
                    modifier = Modifier
                        .then(modifier)
                        .weight(1f), contentAlignment = alignment
                ) {
                    coreTextField()
                    if (placeholder != null && value.isEmpty()) {
                        placeholder()
                    }
                }
                if (drawableEnd != null) {
                    Spacer(modifier = Modifier.width(drawableEndPadding))
                    drawableEnd()
                }
            }
        },
        readOnly = readOnly
    )
}