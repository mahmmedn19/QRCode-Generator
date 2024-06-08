/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("Home")
    data object QrCode : Screen("qr_code")
    data object PaymentOptions : Screen("payment_options")
}