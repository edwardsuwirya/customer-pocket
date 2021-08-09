package com.enigmacamp.mysimpleroom.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.enigmacamp.mysimpleroom.data.entity.Customer
import com.enigmacamp.mysimpleroom.data.entity.CustomerAddress
import com.enigmacamp.mysimpleroom.data.entity.CustomerPocket
import com.enigmacamp.mysimpleroom.utils.AppConstant

@Dao
interface CustomerDao : BaseDao<Customer> {
    @Query("SELECT * FROM ${AppConstant.CUSTOMER_TABLE}")
    fun getCustomers(): List<Customer>

    @Query("SELECT * FROM ${AppConstant.CUSTOMER_TABLE} where customer_id=:id")
    fun getCustomerById(id: Int): Customer

    @Query("SELECT customer_id,address_1,address_2 FROM ${AppConstant.CUSTOMER_TABLE}")
    fun getCustomerAddreses(): List<CustomerAddress>

    @Transaction
    @Query("SELECT * FROM m_customer where customer_id=:id")
    fun getCustomerPocket(id: Int): CustomerPocket
}