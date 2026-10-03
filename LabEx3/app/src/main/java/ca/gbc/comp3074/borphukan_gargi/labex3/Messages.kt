package ca.gbc.comp3074.borphukan_gargi.labex3
import android.content.Context
import java.io.DataOutput

data class Messages(
    val people: String,
    val convo : String,
)

//fun will return the conversation
fun messageInput(context: Context): List<Messages> {

    //loading the names and output from res
    //names=will ask context from res and output  as strng array people_group
    val people = context.resources.getStringArray(R.array.people_group)
    val convo = context.resources.getStringArray(R.array.convo_output)

    return people.zip(convo) {
        people , convo -> Messages (people,convo)
    }

}