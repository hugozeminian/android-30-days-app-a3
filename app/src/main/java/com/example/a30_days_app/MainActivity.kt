package com.example.a30_days_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30_days_app.data.RamadanDatabase
import com.example.a30_days_app.data.RamadanTheme
import com.example.a30_days_app.model.Ramadan_Model
import com.example.a30_days_app.ui.theme._30_Days_AppTheme
import androidx.compose.runtime.*


// MainActivity class
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30_Days_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RamadanApp()
                }
            }
        }
    }
}

// Previews for light and dark themes
@Preview
@Composable
fun RamadanAppPreview() {
    RamadanTheme(darkTheme = false) {
        RamadanApp()
    }
}

@Preview
@Composable
fun RamadanAppDarkThemePreview() {
    RamadanTheme(darkTheme = true) {
        RamadanApp()
    }
}

// Greeting preview
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30_Days_AppTheme {
        RamadanApp()
    }
}

// Main composable function
@Composable
fun RamadanApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.app_title),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            style = MaterialTheme.typography.titleLarge
        )
        RamadanDayList(
            ramadanDayList = RamadanDatabase().loadRamadanQuotes(),
            modifier = Modifier.weight(1f)
        )
    }
}

// Composable function to display the list of Ramadan days
@Composable
fun RamadanDayList(ramadanDayList: List<Ramadan_Model>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(ramadanDayList) { ramadanDay ->
            RamadanCard(
                ramadanDay = ramadanDay,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

// Composable function to display each Ramadan card
@Composable
fun RamadanCard(ramadanDay: Ramadan_Model, modifier: Modifier = Modifier) {
    // State to track whether the description is visible or not
    var isDescriptionVisible by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column(modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))) {
            // Display day number
            Text(
                text = LocalContext.current.getString(ramadanDay.dayNumberId),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
                style = MaterialTheme.typography.titleSmall
            )
            // Display quote
            Text(
                text = LocalContext.current.getString(ramadanDay.quoteId),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
                style = MaterialTheme.typography.titleLarge
            )
            // Display image, clickable to toggle description visibility
            Image(
                painter = painterResource(ramadanDay.imageId),
                contentDescription = stringResource(ramadanDay.descriptionId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.image_size))
                    .clickable {
                        isDescriptionVisible = !isDescriptionVisible // Toggle description visibility on click
                    },
                contentScale = ContentScale.Fit
            )
            // Display description if visible
            if (isDescriptionVisible) {
                Text(
                    text = LocalContext.current.getString(ramadanDay.descriptionId),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
