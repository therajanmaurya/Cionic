package com.cionic.android.core.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.cionic.android.core.model.Cionic
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "Cionic",)
data class CionicEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun CionicEntity.asCionicModel() = Cionic(
    id = id,
    userId = userId,
    title = title,
    body = body
)

fun Cionic.asEntity() = CionicEntity(
    id = id,
    userId = userId,
    title = title,
    body = body
)



@Dao
interface CionicDao {
    @Query("SELECT * FROM cionic WHERE body MATCH :query")
    fun getCionics(query: String): Flow<List<CionicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cionics: List<CionicEntity>)
}
