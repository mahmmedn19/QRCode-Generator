/**
 * @author Mohamed Naser.
 */
package com.monaser.qrcodegen.ui.screens.payment_options

import com.monaser.qrcodegen.ui.base.BaseInteractionListener

interface PaymentInteractionListener: BaseInteractionListener {
    fun onAmountSelected(amount: Int)
    fun onNextClicked()
}