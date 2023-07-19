package com.example.livedatawithflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterScreen(CounterViewModel())
        }
    }
}

class CounterViewModel {

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun incrementCounter() {
        _counter.value++
    }
    fun decrementCounter() {
        _counter.value--
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    val counter = viewModel.counter.collectAsState()
    Column {
        Text(
            text = "Counter: ${counter.value}",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row {
            Button(onClick = { viewModel.incrementCounter() }) {
                Text("Increment")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.decrementCounter() }) {
                Text("Decrement")
            }
        }
    }
}
@Preview
@Composable
fun CounterScreenPreview() {
    CounterScreen(CounterViewModel())
}