package com.cionic.android.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cionic.android.core.ui.Background
import com.cionic.android.core.ui.PurpleGrey80
import com.cionic.android.core.ui.toolbar_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CionicTopAppBar(
    modifier: Modifier = Modifier,
    isCenter: Boolean,
    title: String,
    titleColor: Color = Color.White
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = if (isCenter) Alignment.CenterHorizontally
                else Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = titleColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = toolbar_color,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CionicTopAppBarPreview() {
    CionicTopAppBar(
        modifier = Modifier,
        isCenter = true,
        title = "Cionic Android"
    )
}