package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geoquiz.ui.theme.GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                GeoQuizApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeoQuizApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("GeoQuiz") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        QuizScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    val currentQuestion = questions[currentIndex]

    val onAnswer: (Boolean) -> Unit = { userAnswer ->
        if (!answered) {
            if (userAnswer == currentQuestion.answer) correctCount++
            answered = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = currentQuestion.text,
            style = MaterialTheme.typography.bodyLarge
        )
        // п.2 ЛР: после ответа кнопки TRUE/FALSE становятся невидимыми
        if (!answered) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onAnswer(true) }) { Text("TRUE") }
                Button(onClick = { onAnswer(false) }) { Text("FALSE") }
            }
        }
        // п.3 ЛР: после ответа на последний вопрос NEXT блокируется и становится невидимым
        val isLast = currentIndex == questions.lastIndex
        val showNext = !(isLast && answered)
        if (showNext) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    enabled = answered && !isLast,
                    onClick = {
                        currentIndex++
                        answered = false
                    }
                ) { Text("NEXT >") }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GeoQuizAppPreview() {
    GeoQuizTheme {
        GeoQuizApp()
    }
}
