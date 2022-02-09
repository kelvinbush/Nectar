package com.kelvinbush.nectar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkProduct(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val price: Double,
    val category: ProductCategory,
    val inventory: ProductInventory,
)

@Serializable
data class ProductCategory(
    val id: String,
    val name: String,
    val description: String,
)

@Serializable
data class ProductInventory(
    val id: String,
    val quantity: Int,
)

@Serializable
data class AllNetworkProducts(
    val result: List<NetworkProduct>,
)
