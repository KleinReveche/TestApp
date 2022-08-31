package com.kleinreveche.testapp.features.cafeteria

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kleinreveche.testapp.features.cafeteria.datasource.DataSource
import com.kleinreveche.testapp.features.cafeteria.ui.AccompanimentMenuScreen
import com.kleinreveche.testapp.features.cafeteria.ui.CheckoutScreen
import com.kleinreveche.testapp.features.cafeteria.ui.EntreeMenuScreen
import com.kleinreveche.testapp.features.cafeteria.ui.OrderViewModel
import com.kleinreveche.testapp.features.cafeteria.ui.SideDishMenuScreen
import com.kleinreveche.testapp.features.cafeteria.ui.StartOrderScreen
import com.kleinreveche.testapp.R

enum class CafeteriaScreen(@StringRes val title: Int) {
    Start(title = R.string.cafeteria),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CafeteriaAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    SmallTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CafeteriaApp(modifier: Modifier = Modifier) {
    //Create NavController
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = CafeteriaScreen.valueOf(
        backStackEntry?.destination?.route ?: CafeteriaScreen.Start.name
    )
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            CafeteriaAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CafeteriaScreen.Start.name,
            modifier = modifier.padding(innerPadding),
        ) {
            composable(route = CafeteriaScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(CafeteriaScreen.Entree.name)
                    }
                )
            }

            composable(route = CafeteriaScreen.Entree.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(CafeteriaScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(CafeteriaScreen.SideDish.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateEntree(item)
                    }
                )
            }

            composable(route = CafeteriaScreen.SideDish.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(CafeteriaScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(CafeteriaScreen.Accompaniment.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateSideDish(item)
                    }
                )
            }

            composable(route = CafeteriaScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(CafeteriaScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(CafeteriaScreen.Checkout.name)
                    },
                    onSelectionChanged = { item ->
                        viewModel.updateAccompaniment(item)
                    }
                )
            }

            composable(route = CafeteriaScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(CafeteriaScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(CafeteriaScreen.Start.name, inclusive = false)
                    }
                )
            }
        }
    }
}
