/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.payment_options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monaser.qrcodegen.data.local_data_store.DataStoreManager
import com.monaser.qrcodegen.data.model.PaymentOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel(), PaymentInteractionListener {

    init {
        viewModelScope.launch {
            val userInfo = dataStoreManager.getUserInfo()
            _state.value = _state.value.copy(
                username = userInfo.name
            )
        }
    }

    private val _state = MutableStateFlow(PaymentUiState())
    val state: StateFlow<PaymentUiState> = _state

    private val _effect = MutableSharedFlow<PaymentUiEffect>()
    val effect: SharedFlow<PaymentUiEffect> = _effect

    override fun onAmountSelected(amount: Int) {
        _state.value = _state.value.copy(
            selectedAmount = amount,
            isSelectedAmount = true
        )
    }

    override fun onNextClicked() {
        if (_state.value.selectedAmount == null) {
            _state.value = _state.value.copy(
                isSelectedAmount = false
            )
            return
        }
        viewModelScope.launch {
            dataStoreManager.savePaymentOption(
                PaymentOption(
                    amount = _state.value.selectedAmount!!
                )
            )
            _effect.emit(PaymentUiEffect.NavigateToQrCodeScreen)
        }
    }
}