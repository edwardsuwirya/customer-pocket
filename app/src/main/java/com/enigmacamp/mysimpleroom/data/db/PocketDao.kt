package com.enigmacamp.mysimpleroom.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.enigmacamp.mysimpleroom.data.entity.Pocket
import com.enigmacamp.mysimpleroom.data.entity.PocketWithList
import com.enigmacamp.mysimpleroom.utils.AppConstant

@Dao
interface PocketDao : BaseDao<Pocket> {
    @Transaction
    @Query("SELECT * FROM ${AppConstant.POCKET_TABLE} where pocketId=:pocketId")
    fun getCustomerPocketList(pocketId: Int): List<PocketWithList>
}