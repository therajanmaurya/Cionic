package com.cionic.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CionicEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cionicDao(): CionicDao
}
