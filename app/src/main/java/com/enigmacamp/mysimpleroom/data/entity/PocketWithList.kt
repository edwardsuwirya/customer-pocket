package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PocketWithList(
    @Embedded
    val pocket: Pocket,
    @Relation(parentColumn = "pocketId", entityColumn = "pocketBaseId")
    val pocketList: List<PocketList>
)
