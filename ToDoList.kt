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
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter task") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (textState.text.isNotBlank()) {
                    todoList.add(textState.text)
                    textState = TextFieldValue("") // Clear input field
                }
            }) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(todoList) { task ->
                TaskItem(task = task, onDelete = { todoList.remove(task) })
            }
        }
    }
}

@Composable
fun TaskItem(task: String, onDelete: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = task, modifier = Modifier.weight(1f))
        Button(onClick = onDelete) {
            Text("Delete")
        }
    }
}