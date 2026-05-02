package com.example.count.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.count.data.CountDao
import com.example.count.data.CountEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class CountViewModel(private val countDao: CountDao) : ViewModel() {

    private val _totalCount = MutableStateFlow(0)
    val totalCount: StateFlow<Int> = _totalCount.asStateFlow()

    val history = countDao.getAllCounts()

    init {
        viewModelScope.launch {
            countDao.getAllCounts().collect { entries ->
                _totalCount.value = entries.sumOf { it.increment }
            }
        }
    }

    fun incrementRandomly() {
        viewModelScope.launch {
            val remaining = 11111 - _totalCount.value
            if (remaining > 0) {
                val maxIncrement = minOf(remaining, 500) // Limit random increment for better UX
                val increment = Random.nextInt(1, maxIncrement + 1)
                val newTotal = _totalCount.value + increment
                countDao.insert(CountEntry(value = newTotal, increment = increment))
            }
        }
    }

    fun reset() {
        viewModelScope.launch {
            countDao.deleteAll()
            _totalCount.value = 0
        }
    }
}

class CountViewModelFactory(private val countDao: CountDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountViewModel(countDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
