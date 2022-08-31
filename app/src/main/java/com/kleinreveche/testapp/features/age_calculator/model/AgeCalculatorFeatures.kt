package com.kleinreveche.testapp.features.age_calculator.model

data class AgeCalculatorsFeatures(
    val id: Int,
    val name: String,
    val artifact: String,
    val date: String
)

private const val basePackage = "com.kleinreveche.testapp"

private const val AgeCalculatorLegacyName = "Age Calculator Legacy"
private const val AgeCalculatorLegacyArtifact = "$basePackage.features.age_calculator"

val AgeCalculatorLegacyFeature = AgeCalculatorsFeatures(
    id = 1,
    name = AgeCalculatorLegacyName,
    artifact = AgeCalculatorLegacyArtifact,
    date = "08/19/2022"
)

private const val AgeCalculatorName = "Age Calculator (Material)"
private const val AgeCalculatorArtifact = "$basePackage.features.age_calculator"

val AgeCalculatorFeature = AgeCalculatorsFeatures(
    id = 2,
    name = AgeCalculatorName,
    artifact = AgeCalculatorArtifact,
    date = "08/25/2022"
)

val AgeCalculatorList = listOf(
    AgeCalculatorLegacyFeature,
    AgeCalculatorFeature
)

