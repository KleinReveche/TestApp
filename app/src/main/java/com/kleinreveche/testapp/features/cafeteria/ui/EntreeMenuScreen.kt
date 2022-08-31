package com.kleinreveche.testapp.features.cafeteria.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kleinreveche.testapp.features.cafeteria.datasource.DataSource
import com.kleinreveche.testapp.features.cafeteria.model.MenuItem
import com.kleinreveche.testapp.features.cafeteria.model.MenuItem.EntreeItem

@Composable
fun EntreeMenuScreen(
    options: List<EntreeItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (EntreeItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit
    )
}

@Preview
@Composable
fun EntreeMenuPreview(){
    EntreeMenuScreen(
        options = DataSource.entreeMenuItems,
        onCancelButtonClicked = {},
        onNextButtonClicked = {},
        onSelectionChanged = {}
    )
}
