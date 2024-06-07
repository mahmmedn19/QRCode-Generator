/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.user_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monaser.qrcodegen.data.local_data_store.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFormViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel(), UserFormInteractionListener,UserFormUiEffect {

    private val _state = MutableStateFlow(UserFormUiState())
    val state: StateFlow<UserFormUiState> = _state

    private val _effect = MutableSharedFlow<UserFormUiEffect>()
    val effect: SharedFlow<UserFormUiEffect> = _effect

    override fun onNameChanged(name: String) {
        val isValid = name.isNotBlank()
        _state.value = _state.value.copy(
            name = name,
            isNameValid = isValid,
            errorMessage = if (!isValid) "Name cannot be blank" else null
        )
    }

    override fun onAgeChanged(age: Int) {
        val isValid = age!=0 && age in 12..100
        _state.value = _state.value.copy(
            age = age,
            isAgeValid = isValid,
            errorMessage = if (!isValid) "Age must be between 1 and 150" else null
        )
    }

    override fun onGenderChanged(gender: String) {
        val isValid = gender.equals("Male", true) || gender.equals("Female", true) || gender.equals(
            "Other",
            true
        )
        _state.value = _state.value.copy(
            gender = gender,
            isGenderValid = isValid,
            errorMessage = if (!isValid) "Invalid gender value" else null
        )
    }

    override fun onPhoneNumberChanged(phoneNumber: String) {
        val isValid = phoneNumber.length == 11
        _state.value = _state.value.copy(
            phoneNumber = phoneNumber,
            isPhoneNumberValid = isValid,
            errorMessage = if (!isValid) "Phone number must be 10 digits" else null
        )
    }

    override fun onNextClicked() {
        viewModelScope.launch {
            _state.value.toUser()?.let { dataStoreManager.saveUserInfo(it) }
            _effect.emit(UserFormUiEffect.NavigateToPaymentOptionsScreen)
        }
    }
}
