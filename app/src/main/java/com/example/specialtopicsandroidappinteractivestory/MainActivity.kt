package com.example.specialtopicsandroidappinteractivestory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.specialtopicsandroidappinteractivestory.ui.theme.SpecialTopicsAndroidAppInteractiveStoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpecialTopicsAndroidAppInteractiveStoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    StoryApp()
                }
            }
        }
    }
}

@Composable
fun StoryApp(modifier: Modifier = Modifier) {
    Column {
        Row {
            Text(text = "Story App")
        }
//        the buttons section
        NavigationButtons()
    }
}

@Composable
fun NavigationButtons(modifier: Modifier = Modifier) {
    Row {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "<<")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpecialTopicsAndroidAppInteractiveStoryTheme {
        StoryApp()
    }
}