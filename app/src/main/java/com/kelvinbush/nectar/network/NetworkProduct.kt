package com.kelvinbush.nectar.network

data class NetworkProduct(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val price: Double,
    val category: ProductCategory,
    val inventory: ProductInventory
)

data class ProductCategory(
    val id: String,
    val name: String,
    val description: String,
)

data class ProductInventory(
    val id: String,
    val quantity: Int
)
