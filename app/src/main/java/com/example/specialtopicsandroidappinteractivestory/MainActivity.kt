package com.example.specialtopicsandroidappinteractivestory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var currentStoryChapter by remember { mutableIntStateOf(1) }
    var storyLanguage by remember { mutableStateOf("English") }

    Column (

    ) {
        Row {
            Text(text = "Story App")
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "<<")
            }

//        the buttons section
            NavigationButtons()

            Button(onClick = { /*TODO*/ }) {
                Text(text = ">>")
            }
        }
    }
}

@Composable
fun NavigationButtons() {
    var expanded by remember { mutableStateOf(false) }
    var context by remember { mutableStateOf("Selected Language: English") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.6F)
    ) {


        ClickableText(
            text = AnnotatedString(context),
            onClick = { expanded = !expanded },
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                .padding(12.dp)

            ,

            )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("English") },
                onClick = {
                    context = "Selected Language: English"
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("French") },
                onClick = {
                    context = "Selected Language: French"
                    expanded = false
                }
            )
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