package com.example.shpe_swamphax

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

data class Task(
    var id: Int,
    var title: String,
    var createdAt: Date
)

@RequiresApi(Build.VERSION_CODES.O)
fun getFakeTask(): List<Task> {
    return listOf<Task>(
        Task(1, "Task 1", Date.from(Instant.now())),
        Task(2, "Task 2", Date.from(Instant.now())),
        Task(3, "Task 3", Date.from(Instant.now())),
        Task(4, "Task 4", Date.from(Instant.now())),
    );
}
