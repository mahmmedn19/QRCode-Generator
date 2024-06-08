/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.qr_code

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.monaser.qrcodegen.data.local_data_store.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _state = MutableStateFlow(QRCodeUiState())
    val state: StateFlow<QRCodeUiState> = _state

    init {
        loadUserData()
    }

    fun loadUserData() {
        viewModelScope.launch {
            val userInformation = dataStoreManager.getUserInfo()
            generateDynamicQRCode()
            _state.value = _state.value.copy(userName = userInformation.name)
        }
    }

    private fun generateDynamicQRCode() {
        viewModelScope.launch {
            val userInfo = dataStoreManager.getUserInfo()
            val paymentOption = dataStoreManager.getPaymentOption()
            val dateTimeStamp = System.currentTimeMillis().toString()

            val dataToEncrypt =
                "${userInfo.name}-${userInfo.age}-${userInfo.gender}-${paymentOption}-${dateTimeStamp}"
            val encryptedData = encryptData(dataToEncrypt)

            val bitmap = generateQRCodeBitmap(encryptedData)
            _state.value = _state.value.copy(qrCodeImage = bitmap)
        }
    }

    private fun encryptData(data: String): String {
        return Base64.encodeToString(data.toByteArray(), Base64.DEFAULT)
    }

    private fun generateQRCodeBitmap(data: String): Bitmap? {
        val size = 512
        val qrCodeWriter = QRCodeWriter()
        return try {
            val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }
}