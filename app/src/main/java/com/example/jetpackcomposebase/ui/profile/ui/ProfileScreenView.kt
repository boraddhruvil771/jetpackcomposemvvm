package com.example.jetpackcomposebase.ui.profile.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiNature
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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

    // Handling the response state for Direct Primary Care
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
            val context = LocalContext.current
            val documents =
                (responseDirectPrimaryCare as ResponseHandler.OnSuccessResponse<ResponseData<GetPediatricTelemedicineResponse>>).response

            LaunchedEffect(key1 = documents) {
                documents.listOfData?.let { list ->
                    Toast.makeText(
                        context,
                        "Data fetched successfully: $list",
                        Toast.LENGTH_SHORT
                    ).show()
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

    // Handling the response state for Telemedicine Details
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
            val context = LocalContext.current
            val documents =
                (responseGetTelemedineDetails as ResponseHandler.OnSuccessResponse<ResponseData<GetPediatricTelemedicineResponse>>).response

            LaunchedEffect(key1 = documents) {
                documents.listOfData?.let { list ->
                    Toast.makeText(
                        context,
                        "Data fetched successfully: $list",
                        Toast.LENGTH_SHORT
                    ).show()
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

    ProfileUI()
}

@Composable
fun ProfileUI() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Medical Section
        BenefitSection(
            title = "Medical",
            icon = Icons.Default.MedicalServices,
            employees = listOf(
                Employee("Alex Hales", "Plan A"),
                Employee("Mary Hales", "Plan B"),
                Employee("Roy Hales", "Plan A")
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Vision Section
        BenefitSection(
            title = "Vision",
            icon = Icons.Default.Visibility,
            employees = listOf(
                Employee("Alex Hales", "")
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dental Section
        BenefitSection(
            title = "Dental",
            icon = Icons.Default.EmojiNature, // Substitute with a dental icon if available
            employees = listOf(
                Employee("Alex Hales", "")
            )
        )
    }
}

@Composable
fun BenefitSection(title: String, icon: ImageVector, employees: List<Employee>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0D47A1)) // Dark blue color
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title Icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Employee Plan List
        employees.forEach { employee ->
            EmployeePlanItem(employee)
        }
    }
}

@Composable
fun EmployeePlanItem(employee: Employee) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = employee.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Text(
                text = "Employee${if (employee.plan.isNotEmpty()) " (${employee.plan})" else ""}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFBCA36D) // Gold color
            )
        }
        Button(
            onClick = { /* Handle View Plan action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1E3FF)) // Light blue color
        ) {
            Text(text = "View Plan", color = Color.Black)
        }
    }
}

// Data class for employee information
data class Employee(val name: String, val plan: String)

@Preview
@Composable
fun ProfilePreview() {
    ProfileUI()
}
