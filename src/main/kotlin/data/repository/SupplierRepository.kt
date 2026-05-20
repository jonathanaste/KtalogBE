package com.example.data.repository
import com.example.data.model.CreateSupplierRequest
import com.example.data.model.Supplier
import com.example.data.model.StoreResponse

interface SupplierRepository {
    suspend fun getAllSuppliers(): List<Supplier>
    suspend fun getAllPublicStores(): List<StoreResponse>
    suspend fun getSupplierById(id: String): Supplier?
    suspend fun addSupplier(supplierData: CreateSupplierRequest): Supplier
    suspend fun updateSupplier(id: String, supplierData: Supplier): Boolean
    suspend fun deleteSupplier(id: String): Boolean
}
