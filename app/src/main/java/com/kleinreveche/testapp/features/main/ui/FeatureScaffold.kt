package com.kleinreveche.testapp.features.main.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureScaffold(
    topBarTitle: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { FeatureTopAppBar(title = topBarTitle) },
        content = content
    )

}