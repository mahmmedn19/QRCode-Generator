/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.data.local_data_store

import com.monaser.qrcodegen.data.model.PaymentOption
import com.monaser.qrcodegen.data.model.User

interface DataStoreManager {
    suspend fun saveUserInfo(userInfo: User)
    suspend fun getUserInfo(): User
    suspend fun savePaymentOption(paymentOption: PaymentOption)
    suspend fun getPaymentOption(): PaymentOption
}
