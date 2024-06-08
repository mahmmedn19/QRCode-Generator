/**
 * @author Mohamed Naser.
 */
package com.monaser.qrcodegen.ui.screens.user_info

import com.monaser.qrcodegen.ui.base.BaseInteractionListener

interface UserFormInteractionListener : BaseInteractionListener {
    fun onNameChanged(name: String)
    fun onAgeChanged(age: Int)
    fun onGenderChanged(gender: String)
    fun onPhoneNumberChanged(phoneNumber: String)
    fun onNextClicked()
}