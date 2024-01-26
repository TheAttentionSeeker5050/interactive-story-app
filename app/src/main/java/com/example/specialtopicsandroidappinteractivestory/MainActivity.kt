package com.example.specialtopicsandroidappinteractivestory

import android.net.http.HeaderBlock
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.specialtopicsandroidappinteractivestory.ui.theme.SpecialTopicsAndroidAppInteractiveStoryTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
fun StoryApp(modifier: Modifier = Modifier.fillMaxWidth()) {

    // these are the state handlers for our app
    // these mutate on ui interactions or background processes
    var currentStoryChapter by remember { mutableIntStateOf(1) }
    var storyLanguage by remember { mutableStateOf("English") }

    // mutate the story content, title and image depending on the
    // current story chapter, title and content depend on 2 mutables
    // story chapter and language
    var storyTitle by remember { mutableStateOf( "" ) }
    storyTitle = stringResource(id = R.string.story_part_1_title)
    var storyContent by remember { mutableStateOf("") }
    storyContent = stringResource(id = R.string.story_part_1_content)

    Column (

    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)

        ) {
//            Text(text = "Story App")
            AppHeader()
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 12.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                // if clicked, it will mutate the state of the current chapter
                // but make sure it is between 1 and 6
                if (currentStoryChapter>1) {
                    currentStoryChapter--
                }
            }) {
                Text(text = "<<")
            }

//        the buttons section
            NavigationButtons(
                storyLanguage = storyLanguage,
                onStoryLanguageChange = { newLanguage -> storyLanguage = newLanguage }, // it is a generic implicit default value,
                // meaning we can use it to set the new value anon function values like (String) -> Unit
            )

            Button(onClick = {
                // make sure it doesnt surpass the limits,
                // the story has 6 chapters so in order to advance, the button should be greyed
                // out and not allow to advance chapters if current story chapter is 6
                if (currentStoryChapter<6) {
                    currentStoryChapter++
                }
            }) {
                Text(text = ">>")
            }
        }

        Row {
            ImageDisplayComponent()
        }

        Row (modifier = modifier) {
            StoryContentComponent( modifier = modifier)
        }
    }
}

// the header title component
@Composable
fun AppHeader(
    modifier: Modifier = Modifier
) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = modifier.padding(16.dp),
            color = Color.White
            )
}

@Composable
fun NavigationButtons(
    storyLanguage: String,
    onStoryLanguageChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }
    // instead of defining the selected language inside the component,
    // we will implement what is called state hoisting,
    // meaning lifting the state variable and mutator at a higher level composable
    // so other components can make use of it, such as the story container
//    var context by remember { mutableStateOf(value) } // this is not
    // used anymore


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.6F)
    ) {


        // this will display the current state and expand the
        // dropdown when clicked
        ClickableText(
            text = AnnotatedString("Selected language: $storyLanguage"),
            onClick = { expanded = !expanded },
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                .padding(12.dp)
        )

        // these are the dropdown menu options that will change the state
        // of the language when we select a nrw
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("English") },
                onClick = {
                    onStoryLanguageChange("English")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("French") },
                onClick = {
                    onStoryLanguageChange("French")
                    expanded = false
                }
            )
        }
    }
}

// we are going to create 2 more components: one for displaying the image of
// the story, and one for displaying the paragraph
@Composable
fun ImageDisplayComponent(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp, 16.dp)
) {

    // this image container receives a mutable drawable state that
    // will change the image accordingly to the state of the current page
    // change
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.story_part_1_image), // this will
            // be replaced my mutanble states
            contentDescription = "Story Image"
        )
    }
}

@Composable
fun StoryContentComponent(
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.story_part_1_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(12.dp, 8.dp)
        )
        Text(
            text = stringResource(id = R.string.story_part_1_content),
            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(12.dp, 20.dp)
        )
    }
}

// have arrays to store the resources ids
val imageResources = arrayListOf<Int>(
    R.drawable.story_part_1_image,
    R.drawable.story_part_2_image,
    R.drawable.story_part_3_image,
    R.drawable.story_part_4_image,
    R.drawable.story_part_5_image,
    R.drawable.story_part_6_image,
)

val titleResourcesEnglish = arrayListOf<Int>(
    R.string.story_part_1_title,
    R.string.story_part_2_title,
    R.string.story_part_3_title,
    R.string.story_part_4_title,
    R.string.story_part_5_title,
    R.string.story_part_6_title,
)


// here are some functions to allow us to change the content, image and title
fun changeImage(chapterNumber: Int, language: String) {

}

fun changeTitle(chapterNumber: Int, language: String) {

}

fun changeLanguage(chapterNumber: Int, language: String) {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpecialTopicsAndroidAppInteractiveStoryTheme {
        StoryApp()
    }
}