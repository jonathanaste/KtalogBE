package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Supplier(
    val id: String,
    val name: String,
    val description: String? = null,
    val logoUrl: String? = null,
    val contactPerson: String?,
    val phone: String?,
    val email: String?,
    val cbu: String?,
    val aliasCbu: String?,
    val notes: String?,
    val isActive: Boolean = true
)


@Serializable
data class CreateSupplierRequest(
    val name: String,
    val description: String? = null,
    val logoUrl: String? = null,
    val contactPerson: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val cbu: String? = null,
    val aliasCbu: String? = null,
    val notes: String? = null,
    val isActive: Boolean = true
)
