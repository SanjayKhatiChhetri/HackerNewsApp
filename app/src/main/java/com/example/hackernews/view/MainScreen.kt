package com.example.hackernews.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hackernews.data.model.ItemStory
import com.example.hackernews.modelview.HNViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: HNViewModel, navController: NavController) {
    val storyList = viewModel.topStoryDetails.collectAsState().value
    Scaffold()
    { padding ->
        if (storyList.isEmpty()) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(storyList) { story ->
                StoryItem(story)
                Divider()}
            }
        }
    }
}

@Composable
fun StoryItem(story: ItemStory) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            //.clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(story.url))) }
            .padding(12.dp)
    ) {
        // Story title & link
        Text(
            text = story.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Story metadata layout
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "By: ${story.by}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${story.descendants} comments",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        // Descendants & URL
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Score: ${story.score}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(4.dp)
            )
            story.time.let {
                val date = Date(it * 1000L)
                val format = SimpleDateFormat("MMM dd,yyyy hh:mma", Locale.getDefault())
                Text(
                    text = format.format(date),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    //overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Text(
                text = "Read More",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(story.url)))
                    }
            )
        }
    }
}



/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: HNViewModel, navController: NavController) {
    Button(onClick = { navController.navigate("infoScreen")}) {
        Text("Go to Info", modifier = Modifier.background(color = Color.Black))
    }
    */
/*Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hacker News") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->*//*


        val storyList = viewModel.topStoryDetails.collectAsState().value
        val context = LocalContext.current

        LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
            viewModel.showErrorToastChannel.collectLatest { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        if (storyList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(storyList.size) { index ->
                    StoryItem(story = storyList[index])
                    //Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                }
            }
        }
    }
}
*/
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HackerNewsApp(viewModel: HNViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hacker News") },
                //modifier = Modifier.padding(bottom = 10.dp),
                colors = TopAppBarDefaults
                    .centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        val storyList = viewModel.topStoryDetails.collectAsState().value
        val context = LocalContext.current

        LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
            viewModel.showErrorToastChannel.collectLatest { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        if (storyList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(storyList.size) { index ->
                    StoryItem(story = storyList[index])
                    //Spacer(modifier = Modifier.height(10.dp))
                    Divider(thickness = 2.dp, modifier = Modifier.clip(RoundedCornerShape(5.dp)))
                }
            }
        }
    }
}
*/



/*
@Composable
fun StoryItem(story: ItemStory) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            //.clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(story.url))) }
            .padding(16.dp)
    ) {
        // Story title & link
        Text(
            text = story.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Story metadata layout
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "By: ${story.by}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Score: ${story.score}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${story.descendants} comments",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        // Descendants & URL
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            story.time?.let {
                val date = Date(it * 1000L)
                val format = SimpleDateFormat("MMM dd,yyyy hh:mma", Locale.getDefault())
                Text(
                    text = format.format(date),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    //overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Text(
                text = "Read More",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(story.url)))
                    }
            )
        }
    }
}*/
/*
fun StoryItem(story: ItemStory) {
    // Implementation details for displaying a story's title, author, etc.
    Column(
        modifier = Modifier
            //.clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = story.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "By: ${story.by}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        // Add more details or interaction elements as desired.
    }
}
*/
