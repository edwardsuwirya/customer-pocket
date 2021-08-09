package com.enigmacamp.mysimpleroom.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.mysimpleroom.R
import com.enigmacamp.mysimpleroom.data.db.AppDatabase
import com.enigmacamp.mysimpleroom.data.db.CustomerDao
import com.enigmacamp.mysimpleroom.data.entity.Customer
import com.enigmacamp.mysimpleroom.data.entity.PocketList
import com.enigmacamp.mysimpleroom.data.repository.CustomerRepository
import com.enigmacamp.mysimpleroom.utils.ResourceState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        subscribe()
//        viewModel.registerCustomer()

//        Jangan lupa register customer dulu
        val pocketList = mutableListOf<String>()
        pocketList.add("Tabungan Deposito Ku")
        pocketList.add("Tabungan emasku")
        viewModel.registerPocketList(1, pocketList)
        viewModel.customerPocketList(1)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val db = AppDatabase.getDatabase(this@MainActivity)
                val customerDao = db.customerDao()
                val pocketDao = db.pocketDao()
                val pocketListDao = db.pocketListDao()
                val customerRepository = CustomerRepository(customerDao, pocketDao, pocketListDao)
                return MainActivityViewModel(customerRepository) as T
            }

        }).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.customerPocketListLiveData.observe(this) {
            when (it) {
                is ResourceState.Loading -> Log.d("CustomerActivity", "Loading")
                is ResourceState.Success -> Log.d("CustomerActivity", "onCreate: ${it.data}")
                is ResourceState.Failed -> Log.d("CustomerActivity", "Failed: ${it.message}")
                else -> {
                }
            }
        }

//        viewModel.customerWithPocketLiveData.observe(this) {
//            when (it) {
//                is ResourceState.Loading -> Log.d("CustomerActivity", "Loading")
//                is ResourceState.Success -> Log.d("CustomerActivity", "onCreate: ${it.data}")
//                is ResourceState.Failed -> Log.d("CustomerActivity", "Failed: ${it.message}")
//                else -> {
//                }
//            }
//        }
//
//        viewModel.customerAddressLiveData.observe(this) {
//            when (it) {
//                is ResourceState.Loading -> Log.d("CustomerActivity", "Loading")
//                is ResourceState.Success -> Log.d("CustomerActivity", "onCreate: ${it.data}")
//                is ResourceState.Failed -> Log.d("CustomerActivity", "Failed: ${it.message}")
//                else -> {
//                }
//            }
//        }
    }
}