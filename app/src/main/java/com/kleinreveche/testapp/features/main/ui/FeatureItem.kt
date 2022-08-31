package com.kleinreveche.testapp.features.main.ui

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
import com.kleinreveche.testapp.features.main.model.Features

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureItem(
    feature: Features,
    onClick: (feature: Features) -> Unit
) {
    OutlinedCard(
        onClick = { onClick(feature) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(FeatureItemPadding),
            horizontalArrangement = Arrangement.spacedBy(FeatureItemPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f, fill = true),
                verticalArrangement = Arrangement.spacedBy(FeatureItemTextPadding)
            ) {
                Text(
                    text = feature.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = feature.artifact,
                    style = MaterialTheme.typography.bodySmall,
                )
                        Text(
                        text = feature.date,
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

private val FeatureItemPadding = 16.dp
private val FeatureItemTextPadding = 8.dp
