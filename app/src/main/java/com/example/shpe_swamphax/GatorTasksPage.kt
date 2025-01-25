package com.example.shpe_swamphax

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import java.util.Locale
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GatorPage(viewModel: TaskViewModel, modifier: Modifier = Modifier){

    val tasks by viewModel.taskList.observeAsState() // do this first
    var inputText by remember { // do this after showing the tasks
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(8.dp)
    ){
        if (tasks?.isNotEmpty() == true){
            LazyColumn(
                content = {
                    itemsIndexed(tasks!!, key = { _, item -> item.id }) { _: Int, item: Task ->
                        TaskItem(item = item, onDelete = {
                            Log.d("TaskManager", "Task deleted: ${item.id}")
                            viewModel.deleteTask(item.id)})
                    }
                }
            )
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No tasks yet",
                fontSize = 24.sp
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // text field and button to add task
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                OutlinedTextField(value = inputText, onValueChange = {
                    inputText = it
                })
                Button(onClick = {
                    if(inputText.isNotEmpty())
                        viewModel.addTask(inputText)
                    inputText = ""
                    Log.d("TaskManager", "Task added: ${viewModel.taskList.value}")
                }) {
                    Text(text = "Add")
                }
            }
        }

    }
}

@Composable
fun TaskItem(item: Task, onDelete: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(text = SimpleDateFormat("hh:mm a, MM/dd", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray)

            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
                )
        }
        IconButton(onClick = onDelete){
            Icon(painter = painterResource(id=R.drawable.baseline_delete_forever_24),
                contentDescription = "Delete",
                tint = Color.White)
        }
    }
}