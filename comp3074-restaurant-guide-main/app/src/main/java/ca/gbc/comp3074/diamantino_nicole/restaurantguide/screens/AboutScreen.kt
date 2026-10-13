package ca.gbc.comp3074.diamantino_nicole.restaurantguide.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun AboutScreen(onBack: () -> Unit){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Restaurant Guide",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your personal guide to restaurants you love!",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Restaurant-List",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(24.dp))

        RestaurantListItem("Italian's")
        HorizontalDivider()

        RestaurantListItem("Tacos-Locos")
        HorizontalDivider()

        Spacer(modifier = Modifier.height(24.dp))

        //button provides a back action
        Button(onClick = onBack) {
            Text(text = "Back")
        }

    }
}
@Composable
//RestaurantListItem builds a reusable row for each iyem
fun RestaurantListItem(name : String){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }
}