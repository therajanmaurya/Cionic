package com.cionic.android.core.network.retrofit

import com.cionic.android.core.model.Cionic
import com.cionic.android.core.network.CionicNetworkDataSource
import kotlinx.serialization.Serializable
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetwork @Inject constructor(
    okhttpCallFactory: Call.Factory,
) : CionicNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(CIONIC_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .callFactory(okhttpCallFactory)
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getCionics(): List<Cionic> = networkApi.getCionics()
}

/**
 * Retrofit API declaration
 */
private interface RetrofitNetworkApi {
    @GET(value = "posts")
    suspend fun getCionics(): List<Cionic>
}

private const val CIONIC_BASE_URL = "https://jsonplaceholder.typicode.com"
