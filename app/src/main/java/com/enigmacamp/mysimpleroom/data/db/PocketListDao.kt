package com.enigmacamp.mysimpleroom.data.db

import androidx.room.Dao
import androidx.room.Transaction
import com.enigmacamp.mysimpleroom.data.db.BaseDao
import com.enigmacamp.mysimpleroom.data.entity.PocketList

@Dao
interface PocketListDao : BaseDao<PocketList> {
    @Transaction
    fun insertBulk(dataList: List<PocketList>) {
        dataList.forEach {
            insert(it)
        }
    }
}