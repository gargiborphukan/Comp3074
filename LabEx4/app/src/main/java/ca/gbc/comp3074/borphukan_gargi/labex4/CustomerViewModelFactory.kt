package ca.gbc.comp3074.borphukan_gargi.labex4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


//customer view model requires a dao excess obj we can const
//the actual obj and provide the argument in this factory so it knows hoe
//to cerate the view model

class CustomerViewModelFactory (
    private val customerdao: CustomerDAO
    ): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(customerdao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
