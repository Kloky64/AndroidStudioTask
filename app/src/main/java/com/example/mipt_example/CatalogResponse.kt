package com.example.mipt_example

import kotlinx.serialization.Serializable

@Serializable
data class CatalogResponse (
    val nearest: List<NetRestaurant>,
    val popular: List<NetRestaurant>,
    val commercial: Commercial
)

@Serializable
data class NetRestaurant (
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class Commercial (
    val picture: String,
    val url: String
)