package com.example.jetpackcomposebase.ui.custom_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * This is Common Divider Widget for app
 *
 */
@Composable
fun CustomDividerView(color: Color, thickness: Int, modifier: Modifier) {
    HorizontalDivider(
        modifier = modifier
            .alpha(1f)
            .padding(top = 8.dp, bottom = 8.dp),
        thickness = thickness.dp,
        color = color
    )
}

/**
 * This is Common Textfield Widget for app
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPasswordField: Boolean = false,
    showPassword: Boolean = false,
    setShowPassword: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    indicatorColor: Color = Color.Gray,
    indicatorWidth: Dp = 2.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Gray, fontWeight = FontWeight.SemiBold) },
        interactionSource = interactionSource,
        modifier = modifier
            .drawBehind {
                val strokeWidth = indicatorWidth.value * density
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = indicatorColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (isPasswordField) {
            if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (isPasswordField) {
                if (showPassword) {
                    IconButton(onClick = { setShowPassword(false) }) {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                    }
                } else {
                    IconButton(onClick = { setShowPassword(true) }) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            autoCorrect = false
        )
    )
}

/**
 * This is Common Button Widget for app
 *
 */
@Composable
fun CustomButton(
    text: String,
    backgroundColor: Color,
    modifier: Modifier,
    onCLick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 20.dp, end = 20.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = backgroundColor)
            .padding(all = 10.dp)
            .clickable { onCLick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

    }
}