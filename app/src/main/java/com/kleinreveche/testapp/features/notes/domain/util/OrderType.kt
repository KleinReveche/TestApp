package com.kleinreveche.testapp.features.notes.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
