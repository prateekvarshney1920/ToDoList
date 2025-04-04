import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true , showSystemUi = true )
@Composable
fun TodoApp() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var todoList = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = textState,  // Yha pe yeh TextField ko TextState se bind krke rakhega , jo bhi user type krega yha store ho jayega
                onValueChange = { textState = it },  // user type krega to voh update karta rahega
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter task") }  // hint text ke liye use karte hai placeholder
            )
            Spacer(modifier = Modifier.width(8.dp)) // space dene ke liye
            Button(onClick = {
                if (textState.text.isNotBlank()) {  // empty text ko add nhi karega
                    todoList.add(textState.text)  // user jo input dega usko todoList mai add krta hai
                    textState = TextFieldValue("")
                }
            }) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(todoList) { task ->
                TaskItem(task = task, onDelete = { todoList.remove(task) })  // ek function pass karta task ko delete krne ke liye
            }
        }
    }
}

@Composable
fun TaskItem(task: String, onDelete: () -> Unit) {  // har task item ko display krega
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = task, modifier = Modifier.weight(1f))
        Button(onClick = onDelete) { //  ondelete function ko call krega task ko delete krne ke liye 
            Text("Delete")
        }
    }
}