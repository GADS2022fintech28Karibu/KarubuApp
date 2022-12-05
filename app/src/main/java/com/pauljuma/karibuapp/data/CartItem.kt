package com.pauljuma.karibuapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val productId: String,
    var quantity: Int
)