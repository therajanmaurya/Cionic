package com.cionic.android.core.data

import com.cionic.android.core.database.CionicDao
import com.cionic.android.core.database.asEntity
import com.cionic.android.core.model.Cionic
import com.cionic.android.core.network.CionicNetworkDataSource
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val POST_URL = "https://jsonplaceholder.typicode.com/posts"

interface CionicRepository {
    fun fetchCionics(filterWith: String): Flow<List<Cionic>>
    fun fetchFuelCionics(filterWith: String): Flow<List<Cionic>>
}

class DefaultCionicRepository @Inject constructor(
    private val cionicDao: CionicDao,
    private val cionicNetworkDataSource: CionicNetworkDataSource
) : CionicRepository {

    override fun fetchCionics(filterWith: String): Flow<List<Cionic>> {
        return flow {
            val cionis = cionicNetworkDataSource.getCionics()
            cionicDao.insertAll(cionics = cionis.map { it.asEntity() })
            return@flow emit(cionis.filter {
                it.title.contains(filterWith)
                        || it.body.contains(filterWith)
            })
        }
    }

    override fun fetchFuelCionics(filterWith: String): Flow<List<Cionic>> {
        return flow {
            val (_, _, result) = Fuel.get(POST_URL)
                .awaitStringResponse()
            return@flow emit(Gson().fromJson<List<Cionic>>(result).filter {
                it.title.contains(filterWith)
                        || it.body.contains(filterWith)
            })
        }
    }
}
