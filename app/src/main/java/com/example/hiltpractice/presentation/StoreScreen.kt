package com.example.hiltpractice.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StoreScreen(viewModel: StoreViewModel) {
    val stores by viewModel.stores.collectAsState()
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("가게 목록", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("가게 이름") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                viewModel.addStore(name)
                name = ""
            }) {
                Text("추가")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        stores.forEach {
            Text("• ${it.name}")
        }
    }

}