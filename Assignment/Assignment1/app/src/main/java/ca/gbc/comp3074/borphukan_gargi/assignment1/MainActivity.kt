package ca.gbc.comp3074.borphukan_gargi.assignment1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.compose.ui.text.style.TextAlign


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                PayrollcalculatorScreen()
            }
        }
    }
}
@Composable
fun PayrollcalculatorScreen(){
    val context = LocalContext.current

        //variable sfor input
    var hours by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }
    var taxRate by remember { mutableStateOf("") }
    var payresult by remember { mutableStateOf("") }


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Pay Calculator")

                    OutlinedTextField(
                        value = hours,
                        onValueChange = { hours = it },
                        label = { Text("Hours worked") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        value = rate,
                        onValueChange = { rate = it },
                        label = { Text("Hourly rate()") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    OutlinedTextField(
                        value = taxRate,
                        onValueChange = { taxRate = it },
                        label = { Text("Tax rate()") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    //calculate buttons
                    Button(onClick = {
                        val h = hours.toDoubleOrNull() ?: 0.0
                        val r = rate.toDoubleOrNull() ?: 0.0
                        val t = taxRate.toDoubleOrNull() ?: 0.0

                        //assignmnet formula implementation
                        var pay:  Double
                        var overtime : Double

                        if (h <= 40) {
                            pay = h * r
                            overtime = 0.0
                        }else{
                            pay = 40 * r
                            overtime = (h -40 )* r * 1.5
                        }
                        val total = pay + overtime
                        val tax = pay * t

                        payresult = "Pay: $pay\n" +
                                "Overtime Pay: $overtime\n" +
                                "Total Pay: $total\n" +
                                "Tax: $tax"
                    }) {
                        Text("calculate")
                    }
                    Text(payresult)

                    Button(onClick = {
                        val intent = Intent(context, AboutActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Text("About")
                    }

                    }
                }



