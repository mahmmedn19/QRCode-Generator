/**
 * @author Mohamed Naser.
 */
package com.monaser.qrcodegen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monaser.qrcodegen.ui.screens.payment.PaymentOptionsScreen
import com.monaser.qrcodegen.ui.screens.user_info.UserFormScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavigationProvider provides navController) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                UserFormScreen()
            }
            composable(Screen.PaymentOptions.route) {
                PaymentOptionsScreen()
            }
            composable(Screen.QrCode.route) {
            }
        }
    }
}