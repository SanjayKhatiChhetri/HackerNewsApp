package com.example.hackernews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hackernews.data.errorHandle.NetworkUtils
import com.example.hackernews.data.repository.HNRepositoryImpl
import com.example.hackernews.modelview.HNViewModel
import com.example.hackernews.retrofit.RetrofitInstance
import com.example.hackernews.ui.theme.HackerNewsTheme
import com.example.hackernews.view.InfoScreen
import com.example.hackernews.view.MainScreen


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HNViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hnRepository = HNRepositoryImpl(RetrofitInstance.api)
                return HNViewModel(hnRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Checks for network availability
                    val isNetworkAvailable = NetworkUtils.isNetworkAvailable(this@MainActivity)
                    viewModel.fetchTopStories(isNetworkAvailable)

                    HackerNewsTheme {
                        HackerNewsNavApp(viewModel)
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HackerNewsNavApp(viewModel: HNViewModel) {
    val navController = rememberNavController()

    Scaffold(
        // The top bar could be dynamically adjusted based on the screen, but for simplicity in this example, one top bar is shown.
        topBar = {
            TopAppBar(
                title = {
                    Text(
                    text = stringResource(id = R.string.app_bar_title_hacker_news),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.scrim
                    ) },
                actions = {
                    IconButton(onClick = { navController.navigate("infoScreen") }) {
                        Icon(Icons.Filled.Info, contentDescription = stringResource(id = R.string.app_bar_title_info))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    // Default to MainTopBar, assume logic to switch based on screen
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "mainScreen", modifier = Modifier.padding(innerPadding)) {
            composable("mainScreen") {
                // For main screen, already included the MainTopBar in Scaffold
                MainScreen(viewModel, navController)
            }
            composable("infoScreen") {
                // For info screen or any detail screen, switch to using DetailTopBar
                // This requires replacing the topBar component in Scaffold, which might involve having a state tracking the current screen to adjust topBar dynamically
                InfoScreen(navController)
            }
        }
    }
}
