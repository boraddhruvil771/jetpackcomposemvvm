package com.example.jetpackcomposebase.ui.dashboard.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
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
    val responseHandlercard by viewModel.getDirectPrimaryCareResponse.collectAsState()


    LaunchedEffect(Unit) {
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
    }

    when (responseHandlercard) {
        is ResponseHandler.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnError -> {
            Text(
                text = "Error: ${(responseHandlercard as ResponseHandler.OnError).message}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnSuccessResponse -> {


            lateinit var context: Context
            lateinit var sharedPreferences: SharedPreferences

            // Initialize SharedPreferences
            sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

            // Set a boolean value
            setLoginStatus(true)
            LazyColumn {
                items((responseHandlercard as ResponseHandler.OnSuccessResponse<List<GetPediatricTelemedicineResponse>>).response) { item ->
                    HomeUI(character = item)
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

fun setLoginStatus(b: Boolean) {
    lateinit var sharedPreferences: SharedPreferences
    val editor = sharedPreferences.edit()
    editor.putBoolean("isLogin", b)
    editor.apply()
}


@Composable
fun HomeUI(character: GetPediatricTelemedicineResponse) {
    val imagerPainter =
        rememberAsyncImagePainter(model = character.specialitiesDashBoardItems?.get(0)?.name)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {

            Image(
                painter = imagerPainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "Real name: ${character.specialitiesDashBoardItems}")
                    Text(
                        text = "Actor name: ${
                            character.specialities?.get(0)
                                ?.name
                        }"
                    )
                }
            }
        }
    }
}