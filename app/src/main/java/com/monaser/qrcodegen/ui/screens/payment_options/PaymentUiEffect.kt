/**
 * @author Mohamed Naser.
 */
package com.monaser.qrcodegen.ui.screens.payment_options

import com.monaser.qrcodegen.ui.base.BaseUiEffect

sealed interface PaymentUiEffect : BaseUiEffect {
    data object NavigateToQrCodeScreen : PaymentUiEffect
}