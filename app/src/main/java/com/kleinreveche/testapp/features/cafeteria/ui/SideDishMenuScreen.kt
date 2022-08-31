package com.kleinreveche.testapp.features.cafeteria.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kleinreveche.testapp.features.cafeteria.datasource.DataSource
import com.kleinreveche.testapp.features.cafeteria.model.MenuItem
import com.kleinreveche.testapp.features.cafeteria.model.MenuItem.SideDishItem

@Composable
fun SideDishMenuScreen(
    options: List<SideDishItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (SideDishItem) -> Unit,
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
fun SideDishMenuPreview(){
    SideDishMenuScreen(
        options = DataSource.sideDishMenuItems,
        onNextButtonClicked = {},
        onCancelButtonClicked = {},
        onSelectionChanged = {}
    )
}
