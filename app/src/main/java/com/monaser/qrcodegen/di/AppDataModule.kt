/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.di

import android.content.Context
import com.monaser.qrcodegen.data.local_data_store.DataStoreManager
import com.monaser.qrcodegen.data.local_data_store.DataStoreManagerImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {
    // here injects data store
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManagerImp(context)
    }
}