package com.example.jetpackcomposebase.ui.dashboard.ui

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.DocumentsResponseModel
import com.example.jetpackcomposebase.ui.dashboard.viewmodel.HomeViewModel

@Composable
fun HomeScreenView(
    navController: NavController,
    drawerState: DrawerState,
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
    circularProgress: (Boolean) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    // State variables
    val responsedocumentResponse by viewModel.documentResponse.collectAsState()

    // Ensure that the API call is made only once when the HomeScreenView is composed
    LaunchedEffect(Unit) {
        // Set up the toolbar and bottom bar visibility
        topBar(
            ToolBarData(
                title = "Home",
                isVisible = true,
                isDrawerIcon = true,
                isBackIconVisible = false,
                isTitleVisible = true
            )
        )
        bottomBarVisibility(true)

        viewModel.callDocumentsAPI()
    }

    // Handling the response state
    when (responsedocumentResponse) {
        is ResponseHandler.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnError -> {
            Text(
                text = "Error: ${(responsedocumentResponse as ResponseHandler.OnError).message}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnSuccessResponse -> {
            // Fetch and display the list of documents
            val context = LocalContext.current
            val documents =
                (responsedocumentResponse as ResponseHandler.OnSuccessResponse<ResponseData<DocumentsResponseModel>>).response

            LaunchedEffect(key1 = documents) {
                documents.listOfData?.let { list ->
                    Toast.makeText(
                        context,
                        "data fetched successfully: $list",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            documents.data?.let { list ->
                LazyColumn {
                    items(list.size) { index ->
                        HomeUICard(character = list) // Pass each item to HomeUI
                    }
                }
            }
        }

        else -> {
            Text(
                text = "No data available",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
    }
}

@Composable
// Define the HomeUI function to display character details
fun HomeUICard(character: DocumentsResponseModel) {
    val context = LocalContext.current

    val imagePainter = rememberAsyncImagePainter(
        model = character.get(index = 1).thumbnail_link,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        error = painterResource(id = R.drawable.baseline_arrow_back_ios_24)
    )

    // Main card layout
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Thumbnail Image
            Image(
                painter = imagePainter,
                contentDescription = "Thumbnail",
                modifier = Modifier
                    .size(60.dp) // Set the size of the thumbnail
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Title, Description, and Date Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                // File Type and Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    character.get(index = 1).fileType?.let {
                        Text(
                            text = it.uppercase(), // E.g., PDF or VIDEO
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.Blue // Set color based on file type
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Document Title
                Text(
                    text = character.get(index = 1).fileName.toString(),
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Date
                Text(
                    text = character.get(index = 1).createdAt.toString(),
                    color = Color.Gray
                )
            }

            // "View" Button
            TextButton(
                onClick = {
                    val url = character.get(index = 1).en_link
                    openUrlOrDownload(context, url)
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "View")
            }
        }
    }
}

fun openUrlOrDownload(context: Context, url: String?) {
    if (url!!.endsWith(".pdf") || url.endsWith(".doc") || url.endsWith(".zip")) {
        // Download the file
        Toast.makeText(context, "File Downloading started", Toast.LENGTH_SHORT).show()
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("File Download")
            .setDescription("Downloading file...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                url!!.substringAfterLast("/")
            )

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    } else {
        // Open in a web browser (CustomTabsIntent or default browser)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}

