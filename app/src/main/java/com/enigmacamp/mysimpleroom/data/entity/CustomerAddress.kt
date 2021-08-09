package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.ColumnInfo

data class CustomerAddress(
    @ColumnInfo(name = "customer_id")
    val customerId: Int,
    @ColumnInfo(name = "address_1")
    val address1: String,
    @ColumnInfo(name = "address_2")
    val address2: String
)