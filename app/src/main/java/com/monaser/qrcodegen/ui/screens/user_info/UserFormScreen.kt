/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.user_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.monaser.qrcodegen.ui.screens.composables.QRCodeButton
import com.monaser.qrcodegen.ui.screens.composables.QRCodeTextField

@Composable
fun UserFormScreen(
    viewModel: UserFormViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    EventHandler(
        effects = viewModel.effect,
        handleEffect = { effect, navController ->
            when (effect) {
                is UserFormUiEffect.NavigateToPaymentOptionsScreen -> {
                    navController.navigate(Screen.PaymentOptions.route)
                }

                is UserFormViewModel -> TODO()
            }
        }
    )
    UserFormScreenContent(
        listener = viewModel,
        state = state
    )
}

@Composable
fun UserFormScreenContent(
    listener: UserFormInteractionListener,
    state: UserFormUiState
) {
    var showError by remember { mutableStateOf(false) }

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.qr_lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    Box(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LottieAnimation(
                modifier = Modifier
                    .padding(32.dp)
                    .height(120.dp)
                    .width(120.dp)
                    .align(Alignment.CenterHorizontally),
                composition = preloaderLottieComposition,
                progress = { preloaderProgress },
                alignment = Alignment.Center
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Welcome to QR Code Generator",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = "Please fill in the following details",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                    )

                    QRCodeTextField(
                        hint = "Name",
                        onValueChange = listener::onNameChanged,
                        text = state.name,
                        errorMessage = if (showError && !state.isNameValid) "Name cannot be blank" else "",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    QRCodeTextField(
                        hint = "Age",
                        onValueChange = {
                            val ageValue = it.toIntOrNull()
                            if (ageValue != null) {
                                listener.onAgeChanged(ageValue)
                            }
                        },
                        text = state.age?.toString() ?: "",
                        errorMessage = if (showError && !state.isAgeValid) "Age cannot be blank" else "",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )

                    var expanded by remember { mutableStateOf(false) }
                    val genderOptions = listOf("Male", "Female")

                    Box {
                        QRCodeTextField(
                            hint = "Gender",
                            onValueChange = { },
                            text = state.gender,
                            errorMessage = if (showError && !state.isGenderValid) "Please select a gender" else "",
                            oneLineOnly = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next
                            ),
                            trailingIcon = {
                                IconButton(onClick = { expanded = !expanded }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Icon for dropdown"
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            genderOptions.forEach { gender ->
                                DropdownMenuItem(
                                    text = { Text(text = gender) },
                                    onClick = {
                                        listener.onGenderChanged(gender)
                                        expanded = false
                                    })
                            }
                        }
                    }

                    QRCodeTextField(
                        hint = "Phone Number",
                        onValueChange = listener::onPhoneNumberChanged,
                        text = state.phoneNumber,
                        errorMessage = if (showError && !state.isPhoneNumberValid) "Phone number must have 11 digits" else "",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }
            QRCodeButton(
                text = "Next",
                onClick = {
                    showError = true
                    if (!state.isNameValid || !state.isAgeValid || !state.isGenderValid || !state.isPhoneNumberValid) {
                        return@QRCodeButton
                    }
                    listener.onNextClicked()
                }
            )
        }
    }
}