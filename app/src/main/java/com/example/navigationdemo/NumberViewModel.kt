package com.example.navigationdemo

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NumberViewModel : ViewModel() {
    private val _number = MutableStateFlow(0)
    val number: StateFlow<Int> = _number

    private val _dataFromH = MutableStateFlow("")
    val dataFromH: StateFlow<String> = _dataFromH

    fun incrementNumber() {
        _number.value++
    }

    fun updateDataFromH(data: String) {
        _dataFromH.value = data
    }
}