package com.example.jetpackcomposebase.ui.privcacypolicy.ui

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.ui.privcacypolicy.viewmodel.PrivacyPolicyViewmodel
import com.example.jetpackcomposebase.utils.Constants

@Composable
fun PrivacyPolicyUI(
    navController: NavController,
    drawerState: DrawerState,
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
    circularProgress: (Boolean) -> Unit,
    privacyPolicyViewmodel: PrivacyPolicyViewmodel = hiltViewModel()
) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        topBar(
            ToolBarData(
                title = "Privacy Policy",
                isVisible = false,
                isDrawerIcon = false,
                isBackIconVisible = true,
                isTitleVisible = true
            )
        )
        bottomBarVisibility(false)
        circularProgress(false)

    }
    PrivacyPUI(navController = navController, privacyPolicyViewmodel = privacyPolicyViewmodel)

}

@Composable
fun PrivacyPUI(navController: NavController, privacyPolicyViewmodel: PrivacyPolicyViewmodel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // WebView displaying a URL
        WebViewPrivacyPolicy()
        BackHandler {
            navController?.popBackStack()
        }


    }

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPrivacyPolicy() {
    val mUrl = Constants.PRIVATE_POLICY

    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)

            addJavascriptInterface(JavaScriptInterface(context), "AndroidFunction")
            loadUrl(mUrl)
        }
    })
}

class JavaScriptInterface {

    private val context: Context

    constructor(context: Context) {
        this.context = context
    }

    @JavascriptInterface
    fun showToast(str: String) {
        Toast.makeText(context, "Message Received From Java Script $str", Toast.LENGTH_SHORT).show()
    }
}
