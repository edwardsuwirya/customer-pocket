package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerPocket(
    @Embedded val customer: Customer,
    @Relation(parentColumn = "customer_id",
        entityColumn = "customerPocketOwnerId")
    val pocket: Pocket
)
