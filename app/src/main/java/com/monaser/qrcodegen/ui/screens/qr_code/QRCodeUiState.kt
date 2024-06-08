package com.monaser.qrcodegen.ui.screens.qr_code

import android.graphics.Bitmap

data class QRCodeUiState(
    val userName: String = "",
    val qrCode: String = "",
    val qrCodeImage: Bitmap? = null

)
