package com.monaser.qrcodegen.ui.screens.composables

/**
 * @author Mohamed Naser.
 */


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QRCodeButton(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ),
    onClick: () -> Unit,
    text: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = shape,
        colors = color,
        onClick = onClick,
    ) {
        Text(text = text)
    }
}
