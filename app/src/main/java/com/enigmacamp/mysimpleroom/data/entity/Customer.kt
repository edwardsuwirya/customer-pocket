package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.*
import java.util.*

/*
    UUID
    versi 1 = Time & MAC
    versi 2 = Time,MAC, DCE (Security Protocol)
    versi 3 = Hashing namespace (MD5)
    versi 4 = Random
    versi 5 = Hashing namespace (SHA1)

 */
//@Entity(tableName = "m_customer", indices = [Index("customerId", unique = true)])
@Entity(tableName = "m_customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customer_id")
    val id: Int = 0,
//    val customerId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id_card") val idCard: String,
    @ColumnInfo(name = "gender") val gender: String,
    @Embedded val address: Address,
    @Embedded val phone: Phone
)
