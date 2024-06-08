/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.data.local_data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.monaser.qrcodegen.data.model.PaymentOption
import com.monaser.qrcodegen.data.model.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreManagerImp @Inject constructor(context: Context) : DataStoreManager {
    companion object {
        private const val DATA_STORE_NAME = "qr_code_data_store"
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_AGE_KEY = stringPreferencesKey("user_age")
        private val USER_GENDER_KEY = stringPreferencesKey("user_gender")
        private val USER_PHONE_NUMBER_KEY = stringPreferencesKey("user_phone_number")
        private val USER_PAYMENT_OPTION_KEY = stringPreferencesKey("user_payment_option")
    }

    private val Context.preferencesDataStore: DataStore<
            Preferences> by preferencesDataStore(
        name = DATA_STORE_NAME
    )
    private val prefDataStore = context.preferencesDataStore
    override suspend fun saveUserInfo(userInfo: User) {
        prefDataStore.edit {
            it[USER_NAME_KEY] = userInfo.name
            it[USER_AGE_KEY] = userInfo.age.toString()
            it[USER_GENDER_KEY] = userInfo.gender
            it[USER_PHONE_NUMBER_KEY] = userInfo.phoneNumber
        }
    }

    override suspend fun getUserInfo(): User {
        val preferences = prefDataStore.data.first()
        return User(
            name = preferences[USER_NAME_KEY] ?: "",
            age = preferences[USER_AGE_KEY]?.toInt() ?: 0,
            gender = preferences[USER_GENDER_KEY] ?: "",
            phoneNumber = preferences[USER_PHONE_NUMBER_KEY]?:""
        )
    }

    override suspend fun savePaymentOption(paymentOption: PaymentOption) {
        prefDataStore.edit {
            it[USER_PAYMENT_OPTION_KEY] = paymentOption.amount.toString()
        }
    }

    override suspend fun getPaymentOption(): PaymentOption {
        val preferences = prefDataStore.data.first()
        return PaymentOption(
            amount = preferences[USER_PAYMENT_OPTION_KEY]?.toInt() ?: 0
        )
    }

}