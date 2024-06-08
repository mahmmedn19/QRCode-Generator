/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.qr_code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.monaser.qrcodegen.ui.screens.composables.CircularLoadingAnimation
import kotlinx.coroutines.delay

@Composable
fun QRCodeScreen(
    viewModel: QRCodeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    QRCodeScreenContent(
        state,
        viewModel
    )
}

@Composable
fun QRCodeScreenContent(
    state: QRCodeUiState,
    viewModel: QRCodeViewModel
) {
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            for (i in 0..100) {
                delay(50)
                progress = i / 100f
            }
            viewModel.loadUserData()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularLoadingAnimation(progress)
        Text(text = "QR Code for ${state.userName}")
        Spacer(modifier = Modifier.height(16.dp))
        state.qrCodeImage?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Generated QR Code",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}