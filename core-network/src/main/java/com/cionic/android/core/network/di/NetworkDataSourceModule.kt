package com.cionic.android.core.network.di

import com.cionic.android.core.network.CionicNetworkDataSource
import com.cionic.android.core.network.retrofit.RetrofitNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun RetrofitNetwork.binds(): CionicNetworkDataSource
}
