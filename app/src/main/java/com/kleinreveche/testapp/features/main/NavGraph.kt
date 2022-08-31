package com.kleinreveche.testapp.features.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kleinreveche.testapp.features.age_calculator.AgeCalculatorListNavGraph
import com.kleinreveche.testapp.features.age_calculator.util.AgeCalculatorComposable
import com.kleinreveche.testapp.features.age_calculator.util.AgeCalculatorFeatureRoute
import com.kleinreveche.testapp.features.cupcake.CupcakeApp
import com.kleinreveche.testapp.features.cupcake.CupcakeFeatureRoute
import com.kleinreveche.testapp.features.dessert.DessertClickerApp
import com.kleinreveche.testapp.features.dessert.DessertClickerFeatureRoute
import com.kleinreveche.testapp.features.dessert.data.Datasource
import com.kleinreveche.testapp.features.dice.DiceRollerApp
import com.kleinreveche.testapp.features.dice.DiceRollerFeatureRoute
import com.kleinreveche.testapp.features.cafeteria.CafeteriaApp
import com.kleinreveche.testapp.features.cafeteria.CafeteriaFeatureRoute
import com.kleinreveche.testapp.features.main.model.*
import com.kleinreveche.testapp.features.main.ui.Feature
import com.kleinreveche.testapp.features.notes.NotesApp
import com.kleinreveche.testapp.features.notes.NotesFeatureRoute

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeatureRoute
    ) {
        composable(FeatureRoute) {
            Feature(
                features = FeatureList,
                onSpecificationClick = { feature ->
                    when (feature) {
                        AgeCalculatorFeature -> navController.navigate(AgeCalculatorFeatureRoute)
                        DiceRollerFeature -> navController.navigate(DiceRollerFeatureRoute)
                        DessertClickerFeature -> navController.navigate(DessertClickerFeatureRoute)
                        CupcakeFeature -> navController.navigate(CupcakeFeatureRoute)
                        CafeteriaFeature -> navController.navigate(CafeteriaFeatureRoute)
                        NotesFeature -> navController.navigate(NotesFeatureRoute)
                        else -> throw IllegalArgumentException("Unknown Feature")
                    }
                }
            )
        }
        composable(AgeCalculatorFeatureRoute) { AgeCalculatorListNavGraph() }
        composable(DiceRollerFeatureRoute) { DiceRollerApp() }
        composable(DessertClickerFeatureRoute) { DessertClickerApp(desserts = Datasource.dessertList) }
        composable(CupcakeFeatureRoute) { CupcakeApp() }
        composable(CafeteriaFeatureRoute) { CafeteriaApp() }
        composable(NotesFeatureRoute) { NotesApp() }
    }
}

private const val FeatureRoute = "feature"