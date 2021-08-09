package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.ColumnInfo

data class Address(
    @ColumnInfo(name = "address_1") val address1: String,
    @ColumnInfo(name = "address_2") val address2: String,
)
