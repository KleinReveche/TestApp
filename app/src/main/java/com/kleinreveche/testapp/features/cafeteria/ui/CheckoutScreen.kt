package com.kleinreveche.testapp.features.cafeteria.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kleinreveche.testapp.R
import com.kleinreveche.testapp.features.cafeteria.datasource.DataSource
import com.kleinreveche.testapp.features.cafeteria.model.MenuItem
import com.kleinreveche.testapp.features.cafeteria.model.OrderUiState

@Composable
fun CheckoutScreen(
    orderUiState: OrderUiState,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.order_summary),
            fontWeight = FontWeight.Bold
        )
        ItemSummary(item = orderUiState.entree)
        ItemSummary(item = orderUiState.sideDish)
        ItemSummary(item = orderUiState.accompaniment)

        Divider(thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))

        OrderSubCost(
            resourceId = R.string.subtotal,
            price = orderUiState.itemTotalPrice.formatPrice(),
            Modifier.align(Alignment.End)
        )

        OrderSubCost(
            resourceId = R.string.tax,
            price = orderUiState.orderTax.formatPrice(),
            Modifier.align(Alignment.End)
        )

        Text(
            text = stringResource(R.string.total, orderUiState.orderTotalPrice.formatPrice()),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel).uppercase())
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.submit).uppercase())
            }
        }
    }
}

@Composable
fun ItemSummary(
    item: MenuItem?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(item?.name ?: "")
        Text(item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun OrderSubCost(
    @StringRes resourceId: Int,
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(resourceId, price),
        modifier = modifier
    )
}

@Preview
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(
        orderUiState = OrderUiState(
            entree = DataSource.entreeMenuItems[0],
            sideDish = DataSource.sideDishMenuItems[0],
            accompaniment = DataSource.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
        ),
        onNextButtonClicked = {},
        onCancelButtonClicked = {}
    )
}
