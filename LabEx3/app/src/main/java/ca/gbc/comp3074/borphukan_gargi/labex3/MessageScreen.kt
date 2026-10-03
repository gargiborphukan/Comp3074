package ca.gbc.comp3074.borphukan_gargi.labex3

import android.graphics.drawable.Icon
import android.os.Message
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MessageItem( messages: Messages ){
    Row (
        modifier = Modifier.padding(16.dp).padding(16.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Sms,
            contentDescription = "Icon Sms",
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
        Column(
        modifier = Modifier.padding(16.dp).padding(16.dp)
    ) {
        Text(
            text = messages.people,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = messages.convo,
            fontSize = 16.sp
        )
    }

    }

@Composable
fun MessageList(messages: List<Messages>){

    Text(
        text = "Messages",
        fontSize = 24.sp,
        fontWeight = FontWeight.W300,
        fontStyle = FontStyle.Italic
    )

    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        messages.forEach{
                messages -> MessageItem(messages)
            HorizontalDivider()
        }
    }
}


@Composable
fun MessageScreen(modifier: Modifier) {
    val context = LocalContext.current
    val messages = remember{messageInput(context)}

    MessageList(messages)
}