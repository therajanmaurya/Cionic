package com.cionic.android.core.network

import com.cionic.android.core.model.Cionic

interface CionicNetworkDataSource {
    suspend fun getCionics(): List<Cionic>
}
