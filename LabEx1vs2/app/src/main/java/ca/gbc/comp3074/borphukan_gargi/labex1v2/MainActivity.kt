package ca.gbc.comp3074.borphukan_gargi.labex1v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.gbc.comp3074.borphukan_gargi.labex1v2.ui.theme.LabEx1v2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabEx1v2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var labelValue = remember { mutableStateOf(context.resources.getString(R.string.init_value))}
    var i = remember {mutableStateOf(0)}
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "${labelValue.value}",
            modifier = modifier
        )
        Button({
            val values = context.resources.getStringArray(R.array.values)
            i.value = (i.value + 1) % values.size
            labelValue.value = values[i.value]//context.resources.getString(R.string.click_value)
        }) {
            Text(stringResource(R.string.button_label))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabEx1v2Theme {
        Greeting(modifier = Modifier.padding(0.dp))
    }
}