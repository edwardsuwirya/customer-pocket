package com.enigmacamp.mysimpleroom.presentation

import androidx.lifecycle.*
import com.enigmacamp.mysimpleroom.data.entity.*
import com.enigmacamp.mysimpleroom.data.repository.CustomerRepository
import com.enigmacamp.mysimpleroom.utils.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainActivityViewModel(private val customerRepository: CustomerRepository) : ViewModel() {

/*
private val searchCustomerChannel = ConflatedBroadcastChannel<String>()

fun getCustomer() {
searchCustomerChannel.offer("")
}

val customerAddress = searchCustomerChannel.asFlow().flatMapLatest {
flow {
emit(ResourceState.Loading)
try {
//                customerRepository.registerNewCustomer(
//                    Customer(
//                        name = "Jeco",
//                        address1 = "Ragunan",
//                        address2 = "Jakarta"
//                    )
//                )
//                val customerList = customerRepository.getCustomers()
val customerAddressList = customerRepository.getCustomerAddress()
emit(ResourceState.Success(customerAddressList))
} catch (e: Exception) {
emit(ResourceState.Failed("Ooopss..."))
}
}.flowOn(Dispatchers.IO)
}.asLiveData()
*/

    private var _customerLiveData = MutableLiveData<ResourceState<Customer>>()
    val customerAddressLiveData: LiveData<ResourceState<Customer>>
        get() = _customerLiveData
    private var _customerWithPocketLiveData = MutableLiveData<ResourceState<CustomerPocket>>()
    val customerWithPocketLiveData: LiveData<ResourceState<CustomerPocket>>
        get() = _customerWithPocketLiveData

    private var _customerPocketListLiveData = MutableLiveData<ResourceState<List<PocketWithList>>>()
    val customerPocketListLiveData: LiveData<ResourceState<List<PocketWithList>>>
        get() = _customerPocketListLiveData

    fun getCustomer() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _customerAddressLiveData.postValue(ResourceState.Loading)
//            val customerAddressList = customerRepository.getCustomerAddress()
//            _customerAddressLiveData.postValue(ResourceState.Success(data = customerAddressList));
//        }
    }

    /*
         Register customer dengan pocket nya
     */
    fun registerCustomer() {
        viewModelScope.launch(Dispatchers.IO) {
            val newCustomer = Customer(
                name = "Edi",
                address = Address(
                    address1 = "Ragunan",
                    address2 = "Jakarta",
                ),
                idCard = "222",
                gender = "L",
                phone = Phone(mobilePhone1 = "0808111222")
            )
            val pocket = Pocket(pocketName = "Pocket-Customer")
            customerRepository.registerNewCustomer(newCustomer, pocket)
        }
    }

    fun registerPocket(customerId: Long, pocketName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepository.registerPocket(
                Pocket(
                    pocketName = pocketName,
                    customerPocketOwnerId = customerId
                )
            )
        }
    }

    fun customerWithPocket(customerId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val customerPocket = customerRepository.getCustomerPocket(customerId)
            _customerWithPocketLiveData.postValue(ResourceState.Success(data = customerPocket))
        }
    }

    fun registerPocketList(pocketBaseId: Int, pocketName: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val pocketList = mutableListOf<PocketList>()
            pocketName.forEach {
                pocketList.add(
                    PocketList(
                        pocketBaseId = pocketBaseId,
                        pocketListName = it,
                        pocketValue = 0.0,
                    )
                )
            }
            customerRepository.registerPocketList(pocketList)
        }
    }

    fun customerPocketList(pocketId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val customerPocketList = customerRepository.getCustomerPocketList(pocketId)
            _customerPocketListLiveData.postValue(ResourceState.Success(data = customerPocketList))
        }
    }
}