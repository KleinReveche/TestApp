package com.kleinreveche.testapp.features.age_calculator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kleinreveche.testapp.features.age_calculator.model.AgeCalculatorFeature
import com.kleinreveche.testapp.features.age_calculator.model.AgeCalculatorLegacyFeature
import com.kleinreveche.testapp.features.age_calculator.model.AgeCalculatorList
import com.kleinreveche.testapp.features.age_calculator.model.AgeCalculatorsFeatures
import com.kleinreveche.testapp.features.age_calculator.ui.AgeCalculatorListComponent
import com.kleinreveche.testapp.features.age_calculator.util.AgeCalculatorComposable
import com.kleinreveche.testapp.features.age_calculator.util.AgeCalculatorFeatureRoute
import com.kleinreveche.testapp.features.age_calculator.util.AgeCalculatorLegacyComposable

@Composable
fun AgeCalculatorListNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AgeCalculatorListRoute
    ) {
        composable(AgeCalculatorListRoute) {
            AgeCalculatorListComponent(
                ageCalculatorFeatureList = AgeCalculatorList,
                onSpecificationClick = { feature ->
                    when (feature) {
                        AgeCalculatorFeature -> navController.navigate(AgeCalculatorFeatureRoute)
                        AgeCalculatorLegacyFeature -> navController.navigate(AgeCalculatorLegacyFeatureRoute)
                        else -> throw IllegalArgumentException("Unknown Feature")
                    }
                }
            )
        }
        composable(AgeCalculatorFeatureRoute) { AgeCalculatorComposable() }
        composable(AgeCalculatorLegacyFeatureRoute) { AgeCalculatorLegacyComposable() }
    }
}

private const val AgeCalculatorListRoute = "ageCalculatorList"