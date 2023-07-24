package com.cionic.android.core.data.di

import com.cionic.android.core.data.CionicRepository
import com.cionic.android.core.data.DefaultCionicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsCionicRepository(
        cionicRepository: DefaultCionicRepository
    ): CionicRepository
}
