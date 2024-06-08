/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.user_info

import com.monaser.qrcodegen.data.model.User

data class UserFormUiState (
    val name: String = "",
    val age: Int? = null,
    val gender: String = "",
    val phoneNumber: String = "",
    val isNameValid: Boolean = false,
    val isAgeValid: Boolean = false,
    val isGenderValid: Boolean = false,
    val isPhoneNumberValid: Boolean = false,
    val errorMessage: String? = null
)

fun UserFormUiState.toUser() = age?.let {
    User(
        name = name,
        age = it,
        gender = gender,
        phoneNumber = phoneNumber
    )
}