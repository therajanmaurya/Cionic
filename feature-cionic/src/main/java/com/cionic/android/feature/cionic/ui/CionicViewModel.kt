package com.cionic.android.feature.cionic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cionic.android.core.data.CionicRepository
import com.cionic.android.core.model.Cionic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CionicViewModel @Inject constructor(
    private val cionicRepository: CionicRepository
) : ViewModel() {

    private val _cionicUiState = MutableStateFlow<CionicUiState>(CionicUiState.Loading)
    val cionicUiState: StateFlow<CionicUiState> = _cionicUiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        fetchCionics()
    }

    fun fetchCionics(isRefreshing: Boolean = false) {
        // Handing progress ui
        if (isRefreshing) {
            _isRefreshing.update { true }
        } else {
            _cionicUiState.update { CionicUiState.Loading }
        }

        viewModelScope.launch {
            cionicRepository.fetchCionics()
                .catch { throwable ->
                    // show error UI
                    if (isRefreshing) {
                        // if refresh failed then handle ui message as toast
                        _isRefreshing.update { false }
                    } else {
                        _cionicUiState.update { CionicUiState.Error(throwable) }
                    }
                }
                .collect { cionicList ->
                    _isRefreshing.update { false }
                    _cionicUiState.update { CionicUiState.Success(cionicList) }
                }
        }
    }
}

sealed interface CionicUiState {
    data object Loading : CionicUiState
    data class Error(val throwable: Throwable) : CionicUiState
    data class Success(val data: List<Cionic>) : CionicUiState
}
