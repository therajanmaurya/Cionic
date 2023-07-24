package com.cionic.android.feature.cionic.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cionic.android.core.model.Cionic
import com.cionic.android.core.ui.Background
import com.cionic.android.core.ui.MyApplicationTheme
import com.cionic.android.core.ui.components.CionicTopAppBar
import com.cionic.android.core.ui.light_orange
import com.cionic.android.core.ui.light_violet
import com.cionic.android.core.ui.white_transparent
import com.cionic.android.feature.cionic.ui.CionicUiState.Success
import com.cionic.android.feature.cionic.ui.screens.CionicListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CionicScreen(modifier: Modifier = Modifier, viewModel: CionicViewModel = hiltViewModel()) {
    val uiState by viewModel.cionicUiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    var currentCionic by remember { mutableStateOf<Cionic?>(null) }
    val pullRefreshState = rememberPullRefreshState(isRefreshing, {
        viewModel.fetchCionics(true)
    })

    Column {
        CionicTopAppBar(
            modifier = modifier,
            isCenter = true,
            title = "Cionic",
            titleColor = Color.Black
        )
        Divider(thickness = 0.5.dp, color = Color.Black)
        when (uiState) {
            is Success -> {
                CionicScreen(
                    cionics = (uiState as Success).data,
                    isRefreshing = isRefreshing,
                    pullRefreshState = pullRefreshState,
                    onItemClick = { cionic ->
                        showDialog = showDialog.not()
                        currentCionic = cionic
                    },
                    modifier = modifier
                )
            }

            is CionicUiState.Loading -> Loading()
            else -> ErrorUI { viewModel.fetchCionics() }
        }
    }



    if (showDialog) {
        currentCionic?.let {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(it.title) },
                text = { Text(it.body.replace("\n", " ")) },
                confirmButton = { },
                dismissButton = { }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CionicScreen(
    cionics: List<Cionic>,
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    onItemClick: (name: Cionic) -> Unit,
) {
    Column(modifier) {
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                modifier = modifier.background(white_transparent),
                contentPadding = PaddingValues(all = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(cionics) { cionic ->
                    CionicListItem(
                        modifier = modifier,
                        cionic = cionic,
                        background = if (cionic.id % 2 == 0) light_violet else light_orange,
                        onItemClick = onItemClick
                    )
                }
            }
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ErrorUI(onRetry: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Something went wrong, please try again")
            Button(
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Background),
                onClick = onRetry
            ) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

// Previews

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        CionicScreen(
            listOf(
                Cionic(
                    1,
                    1,
                    "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                ),
                Cionic(
                    1,
                    1,
                    "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                ),
                Cionic(
                    1,
                    1,
                    "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                ),
                Cionic(
                    1,
                    1,
                    "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
                ),
            ),
            pullRefreshState = rememberPullRefreshState(true, { }),
            isRefreshing = false,
            onItemClick = {})
    }
}

@Preview
@Composable
fun ErrorUIPreview() {
    ErrorUI { }
}

@Preview
@Composable
private fun LoadingPreview() {
    Loading()
}

