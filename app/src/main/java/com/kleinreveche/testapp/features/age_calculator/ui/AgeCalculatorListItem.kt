package com.kleinreveche.testapp.ageCalculatorItems.age_calculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kleinreveche.testapp.features.age_calculator.model.AgeCalculatorsFeatures

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCalculatorListItem(
    ageCalculatorItem: AgeCalculatorsFeatures,
    onClick: (ageCalculatorItem: AgeCalculatorsFeatures) -> Unit
) {
    OutlinedCard(
        onClick = { onClick(ageCalculatorItem) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(AgeCalculatorListItemPadding),
            horizontalArrangement = Arrangement.spacedBy(AgeCalculatorListItemPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f, fill = true),
                verticalArrangement = Arrangement.spacedBy(AgeCalculatorListItemTextPadding)
            ) {
                Text(
                    text = ageCalculatorItem.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = ageCalculatorItem.artifact,
                    style = MaterialTheme.typography.bodySmall,
                )
                        Text(
                        text = ageCalculatorItem.date,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

private val AgeCalculatorListItemPadding = 16.dp
private val AgeCalculatorListItemTextPadding = 8.dp
