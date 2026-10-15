package ca.gbc.comp3074.borphukan_gargi.labex4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val customerdao: CustomerDAO
) : ViewModel() {
    val customers: Flow<List<Customer>> = customerdao.getAll()

    fun addCustomer(
        name: String,
        age: Int,
        isActive: Boolean
    ) {
        viewModelScope.launch {
            customerdao.insert(
                Customer(
                    name = name,
                    age = age,
                    isActive = isActive
                )
            )
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            customerdao.delete(customer)
        }
    }
}

