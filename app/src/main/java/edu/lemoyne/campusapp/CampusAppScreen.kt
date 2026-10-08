package edu.lemoyne.campusapp
import androidx.activity.compose.BackHandler
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

// --- Class 7: Step1: a counter that remembers ---
@Composable
fun CounterDemo() {
    var count by remember { mutableStateOf(value = 0) }
    Button(
        onClick = {count++}
    ){
        Text(text = "Tapped $count times")


    }


}
// --- Class 5: Step 6: my own greeting ---
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CampusAppTheme {
        Greeting("Android")
    }
}
// --- Class 9: Step 2: one owner fot the data ---
@Composable
fun CampusAppScreen( modifier: Modifier = Modifier) {
    val traits = remember {
        mutableStateListOf("PNG", "JPEG", "GIF", "PDF")
    }
        // --- Class 9: Step 4: Which screen is showing is just state ---
        var currentScreen by rememberSaveable { mutableStateOf( "home")}
        when(currentScreen){
            "home" ->  HomeScreen(
                traits = traits,
                onAddTrail = {traits.add(it)},
                onSeeAll = { currentScreen = "list"}
            )
            "list" -> ListScreen(
                traits = traits,
                onBack = { currentScreen= "home"},
                modifier = modifier

            )
        }
    }



// --- Class 6: Step 1: my own screen ---
@Composable
fun HomeScreen(traits: MutableList<String>,
               onAddTrail: (String) -> Unit,
               onSeeAll: () -> Unit,
               modifier: Modifier = Modifier){
    // --- Class 7: Step 2: the list lives in space
    // --- Class 7: Step 3: what's typed lives in state ---
    var newTrail by remember {mutableStateOf("" )}
    // --- Class 8: Step 2: the error message lives in state too ---
    var error by remember {mutableStateOf<String?>(value = null)}
    // --- Class 6: Step 3: a column, so things stack ---
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 50.dp)

    ) {
        CounterDemo()
        // --- Lab 6 · Task 3: a picture of my own ---
        Image(
            painter = painterResource(id = R.drawable.header), contentDescription = "Profile Picture", contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // --- Class 6: Step 4: real, styling ---
        Text(
            text = "Image Converter",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "What would you like to convert",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = newTrail,
            // --- Class 8: Step 3: the filed itself pushes back ---

            onValueChange = {
                newTrail = it.take(MAX_NAME_LENGTH)
                error = null
            },
            label = { Text("File name") },
            singleLine = true,
            isError = error != null,
            modifier = Modifier.fillMaxWidth()
        )
        error?.let{ message ->

            Text(
                text = message,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp

            )

        }


        //CLass 7: Step 4: the button changes the state ---
        Button(onClick = {
            // --- Class 8: Step 3: check before you add ---
            val problem = validateFileName(input = newTrail, existingFiles = traits)
            if (problem == null) {
                // --- Class 9: Step 2: ask the owner to add it ---
                onAddTrail(newTrail.trim())
                newTrail = ""

            } else {
                error = problem
            }
        },
            // --- Class 8: Step 4: the sign on the door, not blank ---
            enabled = newTrail.isNotBlank()
        ){
            Text("Add file")

        }


        // --- Lab 7 · Task 3: clear all ---

        Button(onClick = {
            traits.clear()
        })
        {
            Text("Clear files")
        }
        Button(
            onClick = onSeeAll
        ) {
            Text("See all files")
        }
        // --- Lab 7 . Task 1: remove the last item ---



        Spacer(modifier = Modifier.height(8.dp))




        //Text(
        //text = "PNG",
        //fontSize = 18.sp
        //)
        // --- Lab 6 · Task 1: Added 3 more lines ---
        //Text(
        //text = "JPEG",
        //fontSize = 18.sp
        //)
        //Text(
        //text = "GIF",
        //fontSize = 18.sp
        //)
        //Text(
        //text = "PDF",
        //fontSize = 18.sp
        //)
// --- Lab 6 · Task 2: footer --- Spacer(modifier = Modifier.height(24.dp))
        //Text(
        //text = "Last updated September 2026",
        //fontSize = 12.sp,
        //color = MaterialTheme.colorScheme.onSurfaceVariant
        //)
// --- Class 7: Step 2: draw whatever is in the list ---
// --- Lab 7 · Task 2: singular and plural ---
        Text(
            text = if (traits.size == 1) "1 File" else "${traits.size} Files",
            fontWeight = FontWeight.Bold
        )
// --- Lab 7 · Task 4: a live character counter ---
        Text(
            text = "${newTrail.length} / 40",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        for( trail in traits) {
            Text(text= trail, fontSize = 18.sp)
        }


    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    CampusAppTheme {
        HomeScreen(
            traits = mutableListOf("PNG",
                "JPEG",
                "GIF",
                "PDF"),
        onAddTrail = {},
        onSeeAll = {})
    }
}
// --- Lab 6 · Task 4: dark mode preview ---

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun HomeScreenDarkPreview() {
   //CampusAppTheme {
        //Surface {
            //HomeScreen() }
    //}

//}
// --- Class 9: Step 3: the second screen
@Composable
fun ListScreen(
    traits: List<String>,
    onBack: () ->Unit,
    modifier: Modifier = Modifier
)
{
    // --- Class 9: Step 6: the phone's back ---
    BackHandler {onBack() }
    Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp)

            ) {
        TextButton(onClick = onBack) {
            Text(text = "back")
        }
        Text(
            text = "All trails",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(16.dp))
        for( trail in traits) {
            Text(text= trail, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(8.dp))

            }

            }
        }


// --- Lab 8 · Task 1: set min length value ---
const val MIN_NAME_LENGTH = 3
const val MAX_NAME_LENGTH = 30
// --- Class 8: Step 1:one rule book for trail names ---
fun validateFileName(input: String, existingFiles: List<String>): String?{
    val name = input.trim()
    return when {
        name.isEmpty() -> "Enter a file name"
        // --- Lab 8 · Task 1: minimum length ---
        name.length < 3 -> "Too short — at least 3 characters"
        // --- Lab 8 · Task 2: my own rule ---
        name.all { it.isDigit() } -> "A name can't be only numbers"
        name.length > MAX_NAME_LENGTH -> "Keep it to $MAX_NAME_LENGTH characters or fewer"
        existingFiles.any { it.equals( name, ignoreCase = true)} -> "$name is already on the list"
        else -> null
    }
}
// --- Class 9 · Step 7: preview the list screen ---
@Preview( showBackground = true)
@Composable
fun ListScreenPreview() {
    CampusAppTheme{
        ListScreen(
            traits = listOf("PNG",
                "JPEG",
                "GIF",
                "PDF"),
            onBack = {}
        )
            }


    }
