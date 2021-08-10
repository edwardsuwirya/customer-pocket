package com.enigmacamp.mysimpleroom.data.repository

import com.enigmacamp.mysimpleroom.data.db.AppDatabase
import com.enigmacamp.mysimpleroom.data.db.CustomerDao
import com.enigmacamp.mysimpleroom.data.db.PocketDao
import com.enigmacamp.mysimpleroom.data.entity.Customer
import com.enigmacamp.mysimpleroom.data.entity.Pocket
import com.enigmacamp.mysimpleroom.data.entity.PocketList
import com.enigmacamp.mysimpleroom.data.db.PocketListDao

class CustomerRepository(
    private val db: AppDatabase
) {
    fun getCustomers() = db.customerDao().getCustomers()
    fun registerNewCustomer(customer: Customer, pocket: Pocket) {
        with(db) {
            runInTransaction {
                val customerId = customerDao().insert(customer)
                val pocketWithId = pocket.copy(customerPocketOwnerId = customerId)
                pocketDao().insert(pocketWithId)
            }
        }

    }

    fun getCustomer(id: Int) = db.customerDao().getCustomerById(id)
    fun getCustomerAddress() = db.customerDao().getCustomerAddreses()
    fun getCustomerPocket(customerId: Int) = db.customerDao().getCustomerPocket(customerId)

    fun registerPocket(pocket: Pocket) = db.pocketDao().insert(pocket)

    fun registerPocketList(pocketList: List<PocketList>) = db.pocketListDao().insertBulk(pocketList)

    fun getCustomerPocketList(pocketId: Int) = db.pocketDao().getCustomerPocketList(pocketId)
}