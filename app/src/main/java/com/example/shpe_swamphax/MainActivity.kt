package com.example.shpe_swamphax

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.shpe_swamphax.ui.theme.SHPE_SWAMPHAXTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            SHPE_SWAMPHAXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GatorPage(viewModel = taskViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
