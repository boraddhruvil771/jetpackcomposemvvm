package com.example.jetpackcomposebase.ui.profile.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
import com.example.jetpackcomposebase.ui.profile.ui.viewmodel.ProfileScreenViewmodel

@Composable
fun ProfileScreenView(
    navController: NavController,
    drawerState: DrawerState,
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
    circularProgress: (Boolean) -> Unit,
    profileScreenViewmodel: ProfileScreenViewmodel = hiltViewModel()
) {
    val title = stringResource(id = R.string.lbl_profile)

    val responseDirectPrimaryCare by profileScreenViewmodel.getDirectPrimaryCareResponse.collectAsState()
    val responseGetTelemedineDetails by profileScreenViewmodel.telemedicineResponse.collectAsState()

    LaunchedEffect(Unit) {
        topBar(
            ToolBarData(
                title = title,
                isVisible = true,
                isDrawerIcon = true,
                isBackIconVisible = false,
                isTitleVisible = true
            )
        )
        bottomBarVisibility(true)
        circularProgress(false)
        profileScreenViewmodel.callGetDirectPrimaryCareResponse()
        profileScreenViewmodel.callGetTelemedicineDetail()

    }
    // Handling the response state
    when (responseDirectPrimaryCare) {
        is ResponseHandler.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnError -> {
            Text(
                text = "Error: ${(responseDirectPrimaryCare as ResponseHandler.OnError).message}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnSuccessResponse -> {
            // Fetch and display the list of documents
            val context = LocalContext.current
            val documents =
                (responseDirectPrimaryCare as ResponseHandler.OnSuccessResponse<ResponseData<GetPediatricTelemedicineResponse>>).response

            LaunchedEffect(key1 = documents) {
                documents.listOfData?.let { list ->
                    Toast.makeText(
                        context,
                        "data fetched successfully: $list",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            documents.listOfData?.let { list ->
                LazyColumn {
/*                    list.get(1)?.let {
                        items(it) { index ->
                            HomeUI(character = list) // Pass each item to HomeUI
                        }
                    }*/
                }
            }
        }

        else -> {
            /*
                        Text(
                            text = "No data available",
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)import com.example.jetpackcomposebase.ui.dashboard.ui.HomeUI
                        )
            */
        }
    }
    when (responseGetTelemedineDetails) {
        is ResponseHandler.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnError -> {
            Text(
                text = "Error: ${(responseGetTelemedineDetails as ResponseHandler.OnError).message}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnSuccessResponse -> {
            // Fetch and display the list of documents
            val context = LocalContext.current
            val documents =
                (responseGetTelemedineDetails as ResponseHandler.OnSuccessResponse<ResponseData<GetPediatricTelemedicineResponse>>).response

            LaunchedEffect(key1 = documents) {
                documents.listOfData?.let { list ->
                    Toast.makeText(
                        context,
                        "data fetched successfully: $list",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            documents.listOfData?.let { list ->
                LazyColumn {
/*                    list.get(1)?.let {
                        items(it) { index ->
                            HomeUI(character = list) // Pass each item to HomeUI
                        }
                    }*/
                }
            }
        }

        else -> {
            /*
                        Text(
                            text = "No data available",
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)import com.example.jetpackcomposebase.ui.dashboard.ui.HomeUI

                        )
            */
        }
    }

    ProfileUI()
}


@Composable
fun ProfileUI() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.lbl_profile),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )

    }
}

@Preview
@Composable
fun ProfilePreview() {
    ProfileUI()
}