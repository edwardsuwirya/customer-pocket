package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "m_pocket")
data class Pocket(
    @PrimaryKey(autoGenerate = true)
    val pocketId: Int = 0,
    val pocketName: String,
    val customerPocketOwnerId: Long
)
