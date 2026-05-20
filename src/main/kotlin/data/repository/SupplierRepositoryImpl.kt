package com.example.data.repository

import com.example.data.model.*
import com.example.plugins.ConflictException
import com.example.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class SupplierRepositoryImpl : SupplierRepository {

    private fun resultRowToSupplier(row: ResultRow) = Supplier(
        id = row[SuppliersTable.id],
        name = row[SuppliersTable.name],
        description = row[SuppliersTable.description],
        logoUrl = row[SuppliersTable.logoUrl],
        contactPerson = row[SuppliersTable.contactPerson],
        phone = row[SuppliersTable.phone],
        email = row[SuppliersTable.email],
        cbu = row[SuppliersTable.cbu],
        aliasCbu = row[SuppliersTable.aliasCbu],
        notes = row[SuppliersTable.notes],
        isActive = row[SuppliersTable.isActive]
    )

    override suspend fun getAllSuppliers(): List<Supplier> = dbQuery {
        SuppliersTable.selectAll().map(::resultRowToSupplier)
    }

    override suspend fun getAllPublicStores(): List<StoreResponse> = dbQuery {
        SuppliersTable
            .selectAll()
            .where { SuppliersTable.isActive eq true }
            .map {
                StoreResponse(
                    id = it[SuppliersTable.id],
                    name = it[SuppliersTable.name],
                    description = it[SuppliersTable.description],
                    logoUrl = it[SuppliersTable.logoUrl],
                    phone = it[SuppliersTable.phone]
                )
            }
    }

    override suspend fun getSupplierById(id: String): Supplier? = dbQuery {
        SuppliersTable
            .selectAll().where { SuppliersTable.id eq id }
            .map(::resultRowToSupplier)
            .singleOrNull()
    }

    override suspend fun addSupplier(supplierData: CreateSupplierRequest): Supplier {
        val newId = UUID.randomUUID().toString()
        return dbQuery {
            val insertStatement = SuppliersTable.insert {
                it[id] = newId
                it[name] = supplierData.name
                it[description] = supplierData.description
                it[logoUrl] = supplierData.logoUrl
                it[contactPerson] = supplierData.contactPerson
                it[phone] = supplierData.phone
                it[email] = supplierData.email
                it[cbu] = supplierData.cbu
                it[aliasCbu] = supplierData.aliasCbu
                it[notes] = supplierData.notes
                it[isActive] = supplierData.isActive
            }
            resultRowToSupplier(insertStatement.resultedValues!!.first())
        }
    }

    override suspend fun updateSupplier(id: String, supplierData: Supplier): Boolean = dbQuery {
        SuppliersTable.update({ SuppliersTable.id eq id }) {
            it[name] = supplierData.name
            it[description] = supplierData.description
            it[logoUrl] = supplierData.logoUrl
            it[contactPerson] = supplierData.contactPerson
            it[phone] = supplierData.phone
            it[email] = supplierData.email
            it[cbu] = supplierData.cbu
            it[aliasCbu] = supplierData.aliasCbu
            it[notes] = supplierData.notes
            it[isActive] = supplierData.isActive
        } > 0
    }

    override suspend fun deleteSupplier(id: String): Boolean = dbQuery {
        val existingProducts = ProductsTable.selectAll().where { ProductsTable.supplierId eq id }.count()

        if (existingProducts > 0) {
            throw ConflictException("Cannot delete supplier. Reassign or delete ${existingProducts} associated product(s) first.")
        }

        SuppliersTable.deleteWhere { SuppliersTable.id eq id } > 0
    }
}
