package edu.lemoyne.campusapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(paddingValues = innerPadding   )

                    )
                }
            }
        }
    }
}

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
// --- Class 6: Step 1: my own screen ---
@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    // --- Class 7: Step 2: the list lives in space
    val traits = remember { mutableStateListOf("PNG", "JPEG", "GIF", "PDF") }
    // --- Class 7: Step 3: what's typed lives in state ---
    var newTrail by remember {mutableStateOf("" )}
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
            onValueChange = {newTrail = it},
            label = { Text("Trail name") },
            modifier = Modifier.fillMaxWidth()
        )


        //CLass 7: Step 4: the button changes the state ---
        Button(onClick = {
            traits.add(newTrail)
            newTrail = ""

        })
        {
            Text("Add file")

        }
        // --- Lab 7 · Task 3: clear all ---

        Button(onClick = {
            traits.clear()
        })
        {
            Text("Clear files")
        }

        // --- Lab 7 . Task 1: remove the last item ---
        Button(onClick = {
            if (traits.isNotEmpty()) {
                traits.removeAt(traits.lastIndex)
            }
        }) {
            Text("Remove last")
        }


        Spacer(modifier = Modifier.height(8.dp))




       Text(
           text = "PNG",
           fontSize = 18.sp
       )
        // --- Lab 6 · Task 1: Added 3 more lines ---
        Text(
            text = "JPEG",
            fontSize = 18.sp
        )
        Text(
            text = "GIF",
            fontSize = 18.sp
        )
        Text(
            text = "PDF",
            fontSize = 18.sp
        )
// --- Lab 6 · Task 2: footer --- Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Last updated September 2026",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
        HomeScreen()
    }
}
// --- Lab 6 · Task 4: dark mode preview ---

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        Surface {
            HomeScreen() }
        }

}
