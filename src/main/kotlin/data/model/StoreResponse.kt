package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    val id: String,
    val name: String,
    val description: String? = null,
    val logoUrl: String? = null,
    val phone: String? = null
)
