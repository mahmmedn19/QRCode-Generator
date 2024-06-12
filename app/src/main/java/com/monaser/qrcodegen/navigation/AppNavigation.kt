/**
 * @author Mohamed Naser.
 */
package com.monaser.qrcodegen.navigation

import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.monaser.qrcodegen.ui.screens.payment_options.PaymentOptionsScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monaser.qrcodegen.ui.screens.qr_code.QRCodeScreen
import com.monaser.qrcodegen.ui.screens.user_info.UserFormScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavigationProvider provides navController) {
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
            },
            sizeTransform = {
                SizeTransform(
                    clip = false,
                    sizeAnimationSpec = { _, _ -> tween(durationMillis = 700) }
                )
            }
        ) {
            composable(Screen.Home.route) {
                UserFormScreen()
            }
            composable(Screen.PaymentOptions.route) {
                PaymentOptionsScreen()
            }
            composable(Screen.QrCode.route) {
                QRCodeScreen()
            }
        }
    }
}