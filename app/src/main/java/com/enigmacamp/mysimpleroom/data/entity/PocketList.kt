package com.enigmacamp.mysimpleroom.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "m_pocket_list")
data class PocketList(
    @PrimaryKey(autoGenerate = true)
    val pocketListId: Int = 0,
    val pocketBaseId: Int,
    val pocketListName: String,
    val pocketValue: Double
)
