package com.example.a08_modulo_a2_05.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a08_modulo_a2_05.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(nav: NavHostController) {
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        delay(5000)
        nav.navigate("login")
    }

    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = "",
            modifier = Modifier.size(120.dp)
        )
    }

}