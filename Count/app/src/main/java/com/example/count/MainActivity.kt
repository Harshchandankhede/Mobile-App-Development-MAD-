package com.example.count

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.count.data.CountDatabase
import com.example.count.ui.CountScreen
import com.example.count.ui.CountViewModel
import com.example.count.ui.CountViewModelFactory
import com.example.count.ui.theme.CountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = CountDatabase.getDatabase(applicationContext)
        val viewModelFactory = CountViewModelFactory(database.countDao())
        
        enableEdgeToEdge()
        setContent {
            CountTheme {
                val viewModel: CountViewModel = viewModel(factory = viewModelFactory)
                CountScreen(viewModel = viewModel)
            }
        }
    }
}
