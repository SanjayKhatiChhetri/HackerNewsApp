package com.example.hackernews.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Hacker News App") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        InfoContent(paddingValues = innerPadding)
    }
}

@Composable
fun InfoContent(paddingValues: PaddingValues) {
    Column(modifier = Modifier
        .padding(16.dp)) {

        Text(text = "HackerNewsApp Overview", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("HackerNewsApp brings the latest stories from Hacker News to your Android device, showcasing modern Kotlin and Jetpack Compose development practices.",
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Features", style = MaterialTheme.typography.headlineSmall)
        FeatureItem(feature = "Latest Stories: Real-time story updates.")
        FeatureItem(feature = "Responsive UI: Smooth adaptation across devices.")
        // Include additional features as necessary

        Spacer(modifier = Modifier.height(16.dp))
        Text("Get Involved", style = MaterialTheme.typography.headlineSmall)
        Text("Contributions are welcome! Visit the project repository to contribute or open an issue.",
            style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
        Text("License: MIT. Please see the LICENSE file for more information.",
            style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
        // Optionally, provide a link to the detailed README or project repository.
        Text("For detailed information, visit our [repository](https://github.com/your/repo).", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun FeatureItem(feature: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text("• ", style = MaterialTheme.typography.bodyLarge)
        Text(feature, style = MaterialTheme.typography.bodyLarge)
    }
}

