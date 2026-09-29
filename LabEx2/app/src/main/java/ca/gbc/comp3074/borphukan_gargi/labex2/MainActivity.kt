package ca.gbc.comp3074.borphukan_gargi.labex2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.compose.ui.text.style.TextAlign
import ca.gbc.comp3074.borphukan_gargi.labex2.ui.theme.LabEx2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabEx2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ActionButtons(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun ActionButtons(modifier: Modifier = Modifier,
                  url: Uri = "https://gerorgebrown.ca".toUri(),
                  phone: Uri = "tel:416575000".toUri(),
                  location: Uri =("geo:0,0?g="+Uri.encode("160 Kendal Ave, Toronto")).toUri()){

    var cnt = remember { mutableIntStateOf(0) }
    var step = remember { mutableIntStateOf(1) }


    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Application Logo",
                modifier = Modifier.width(200.dp).height(100.dp)
            )
        }

        //Counter
        Text(
            text = cnt.intValue.toString(),
        )



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                   cnt.intValue += step.intValue
                }
            ){
                Text("Add")
            }
            Button(
                onClick = {
                    cnt.intValue -= step.intValue
                }

            ){
                Text("Substract")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    cnt.intValue = 0
                    step.intValue = 1
                }
            ){
                Text("Reset")
            }
            Button(
                onClick = {
                     step.intValue = 2
                }

            ){
                Text("Step")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabEx2Theme {
        ActionButtons()
    }
}