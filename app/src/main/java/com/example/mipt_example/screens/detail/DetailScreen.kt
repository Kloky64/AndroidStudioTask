package com.example.mipt_example.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Hello, $name", fontSize = 24.sp)
    }
}