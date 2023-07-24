package com.cionic.android.feature.cionic.ui

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.cionic.android.core.data.CionicRepository
import com.cionic.android.core.model.Cionic

@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class CionicViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = CionicViewModel(FakeCionicRepository())
        assertEquals(viewModel.cionicUiState.first(), CionicUiState.Loading)
    }

    @Test
    fun uiState_onItemSaved_isDisplayed() = runTest {
        val viewModel = CionicViewModel(FakeCionicRepository())
        assertEquals(viewModel.cionicUiState.first(), CionicUiState.Loading)
    }
}

private class FakeCionicRepository : CionicRepository {

    private val data = mutableListOf<List<Cionic>>()

    override fun fetchCionics(): Flow<List<Cionic>> {
        return flow { emit(emptyList()) }
    }
}
