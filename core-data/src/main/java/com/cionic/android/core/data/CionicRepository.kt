package com.cionic.android.core.data

import com.cionic.android.core.database.CionicDao
import com.cionic.android.core.database.asEntity
import com.cionic.android.core.model.Cionic
import com.cionic.android.core.network.CionicNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CionicRepository {

    fun fetchCionics(filterWith: String): Flow<List<Cionic>>
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
}
