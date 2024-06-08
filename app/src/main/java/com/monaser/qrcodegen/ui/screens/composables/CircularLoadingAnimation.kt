/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoadingAnimation(progress: Float) {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        drawCircle(
            color = Color.LightGray,
            style = Stroke(width = 8.dp.toPx())
        )
        drawArc(
            color = Color(0xFF009688),
            startAngle = -90f,
            sweepAngle = 360 * progress,
            useCenter = false,
            style = Stroke(width = 8.dp.toPx())
        )
    }
}