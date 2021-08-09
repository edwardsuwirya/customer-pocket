package com.enigmacamp.mysimpleroom.data.repository

import com.enigmacamp.mysimpleroom.data.db.CustomerDao
import com.enigmacamp.mysimpleroom.data.db.PocketDao
import com.enigmacamp.mysimpleroom.data.entity.Customer
import com.enigmacamp.mysimpleroom.data.entity.Pocket
import com.enigmacamp.mysimpleroom.data.entity.PocketList
import com.enigmacamp.mysimpleroom.data.db.PocketListDao

class CustomerRepository(
    private val customerDao: CustomerDao,
    private val pocketDao: PocketDao,
    private val pocketListDao: PocketListDao
) {
    fun getCustomers() = customerDao.getCustomers()
    fun registerNewCustomer(customer: Customer) = customerDao.insert(customer)
    fun getCustomer(id: Int) = customerDao.getCustomerById(id)
    fun getCustomerAddress() = customerDao.getCustomerAddreses()
    fun getCustomerPocket(customerId: Int) = customerDao.getCustomerPocket(customerId)

    fun registerPocket(pocket: Pocket) = pocketDao.insert(pocket)

    fun registerPocketList(pocketList: List<PocketList>) = pocketListDao.insertBulk(pocketList)

    fun getCustomerPocketList(pocketId: Int) = pocketDao.getCustomerPocketList(pocketId)
}