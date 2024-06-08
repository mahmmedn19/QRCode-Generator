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
        val isValid = age!=0
        _state.value = _state.value.copy(
            age = age,
            isAgeValid = isValid,
            errorMessage = if (!isValid) "Age not valid" else null
        )
    }

    override fun onGenderChanged(gender: String) {
        val isValid = gender.equals("Male", true) || gender.equals("Female", true)
        _state.value = _state.value.copy(
            gender = gender,
            isGenderValid = isValid,
            errorMessage = if (!isValid) "Please select a gender" else null
        )
    }

    override fun onPhoneNumberChanged(phoneNumber: String) {
        val isValid = phoneNumber.length == 11 && phoneNumber.startsWith("01")
        _state.value = _state.value.copy(
            phoneNumber = phoneNumber,
            isPhoneNumberValid = isValid,
            errorMessage = if (!isValid) "Phone number must begin with 01..x and have 11 digits" else null
        )
    }

    override fun onNextClicked() {
        viewModelScope.launch {
            _state.value.toUser()?.let { dataStoreManager.saveUserInfo(it) }
            _effect.emit(UserFormUiEffect.NavigateToPaymentOptionsScreen)
        }
    }
}
