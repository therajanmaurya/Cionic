/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cionic.android.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.cionic.android.core.data.DefaultCionicRepository
import com.cionic.android.core.database.Cionic
import com.cionic.android.core.database.CionicDao

/**
 * Unit tests for [DefaultCionicRepository].
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class DefaultCionicRepositoryTest {

    @Test
    fun cionics_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultCionicRepository(FakeCionicDao())

        repository.add("Repository")

        assertEquals(repository.cionics.first().size, 1)
    }

}

private class FakeCionicDao : CionicDao {

    private val data = mutableListOf<Cionic>()

    override fun getCionics(): Flow<List<Cionic>> = flow {
        emit(data)
    }

    override suspend fun insertCionic(item: Cionic) {
        data.add(0, item)
    }
}
