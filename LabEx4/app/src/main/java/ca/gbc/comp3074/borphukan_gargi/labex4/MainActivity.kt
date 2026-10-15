package ca.gbc.comp3074.borphukan_gargi.labex4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ca.gbc.comp3074.borphukan_gargi.labex4.ui.theme.LabEx4Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = AppDatabase.getDatabase(applicationContext)
        val viewModel = CustomerViewModelFactory(database.customerdao())
            .create(CustomerViewModel::class.java)

        setContent {
            LabEx4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        CustomerApp(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomerApp(viewModel: CustomerViewModel) {
    val customers = viewModel.customers.collectAsState(initial = emptyList())
    CustomerScreen(
        customers = customers.value,
        onAddCustomer = viewModel::addCustomer,
        onDeleteCustomer = viewModel::deleteCustomer
    )
}

@Composable
fun CustomerScreen(
    customers: List<Customer>,
    onAddCustomer: (String, Int, Boolean) -> Unit,
    onDeleteCustomer: (Customer) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val ageTxt = remember { mutableStateOf("") }
    val isActive = remember { mutableStateOf(false) }
    val message = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Lab Exercise 4", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Customers", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = ageTxt.value,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    ageTxt.value = newValue
                }
            },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Active")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isActive.value,
                onCheckedChange = { isActive.value = it }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        message.value?.let { msg ->
            Text(text = msg, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = {
                val nameVal = name.value
                val age = ageTxt.value.toIntOrNull()
                if (nameVal.isBlank()) {
                    message.value = "Name is required"
                } else if (age == null) {
                    message.value = "Age is required"
                } else {
                    onAddCustomer(nameVal.trim(), age, isActive.value)
                    name.value = ""
                    ageTxt.value = ""
                    isActive.value = false
                    message.value = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Customer")
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomerList(
            customers = customers,
            onDeleteCustomer = onDeleteCustomer
        )
    }
}

@Composable
fun CustomerList(
    customers: List<Customer>,
    onDeleteCustomer: (Customer) -> Unit
) {
    if (customers.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No customers found")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = customers,
                key = { customer -> customer.id }
            ) { customer ->
                CustomerItem(
                    customer = customer,
                    onDeleteCustomer = onDeleteCustomer
                )
            }
        }
    }
}

@Composable
fun CustomerItem(
    customer: Customer,
    onDeleteCustomer: (Customer) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "${customer.name} (${customer.age})")
                Text(text = "Active: ${if (customer.isActive) "Active" else "Inactive"}")
            }
            Button(onClick = { onDeleteCustomer(customer) }) {
                Text(text = "Delete")
            }
        }
    }
}
