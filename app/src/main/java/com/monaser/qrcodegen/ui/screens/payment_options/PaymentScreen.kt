/**
 * @author Mohamed Naser.
 */
package com.monaser.qrcodegen.ui.screens.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.monaser.qrcodegen.R
import com.monaser.qrcodegen.navigation.Screen
import com.monaser.qrcodegen.ui.screens.composables.EventHandler
import com.monaser.qrcodegen.ui.screens.payment_options.PaymentInteractionListener
import com.monaser.qrcodegen.ui.screens.payment_options.PaymentUiEffect
import com.monaser.qrcodegen.ui.screens.payment_options.PaymentUiState
import com.monaser.qrcodegen.ui.screens.payment_options.PaymentViewModel

@Composable
fun PaymentOptionsScreen(
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    EventHandler(
        effects = viewModel.effect,
        handleEffect = { effect, navController ->
            when (effect) {
                is PaymentUiEffect.NavigateToQrCodeScreen -> {
                    navController.navigate(Screen.QrCode.route)
                }
            }
        }
    )
    PaymentScreenContent(
        state = state,
        listener = viewModel
    )
}

@Composable
fun PaymentScreenContent(
    state: PaymentUiState,
    listener: PaymentInteractionListener
) {
    val amounts = listOf(5, 7, 10)
    val preloaderLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.qr_lottie))
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier
                .padding(32.dp)
                .height(160.dp)
                .width(160.dp),
            composition = preloaderLottieComposition,
            progress = { preloaderProgress },
            alignment = Alignment.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            state.username?.let {
                Text(
                    text = "Hello, ",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "$it!",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Select an Amount",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(amounts) { amount ->
                val isSelected = state.selectedAmount == amount
                Button(
                    onClick = { listener.onAmountSelected(amount) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFF009688) else Color.LightGray,
                        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
                    ),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "$amount Egp",
                        fontSize = 16.sp,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                listener.onNextClicked()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Next")
        }

        if (!state.isSelectedAmount) {
            Text(
                text = "Please select an amount",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
