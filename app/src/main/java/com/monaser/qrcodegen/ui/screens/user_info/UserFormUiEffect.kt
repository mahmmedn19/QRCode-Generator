/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.user_info

import com.monaser.qrcodegen.ui.base.BaseUiEffect

sealed interface UserFormUiEffect: BaseUiEffect  {
    data object NavigateToPaymentOptionsScreen: UserFormUiEffect

}