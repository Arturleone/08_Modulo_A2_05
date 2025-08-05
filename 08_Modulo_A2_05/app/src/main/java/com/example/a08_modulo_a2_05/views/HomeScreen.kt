package com.example.a08_modulo_a2_05.views

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a08_modulo_a2_05.BaseScaffold
import com.example.a08_modulo_a2_05.motdGerado
import com.example.a08_modulo_a2_05.network.API
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(nav: NavHostController) {
    var motd by remember { mutableStateOf<String?>(null) }
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    val Toast: (String) -> Unit = {
        Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
    }
    val prefs = ctx.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
    val email = prefs.getString("email", "")

    LaunchedEffect(Unit) {
        try {
            val texto: motdGerado = API.service.gerarMOTD()
            motd = texto.MOTD
        } catch (e: Exception) {
            Toast("DEu PRoblema")
        }
    }
    BaseScaffold(nav, "HomeScreen") { content ->
        Column (modifier = Modifier.fillMaxSize().padding(8.dp)){
            Text("Bem-vindo, ${email.toString().substring(0, 11)}", modifier = Modifier.align(Alignment.CenterHorizontally))
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                motd?.let {
                    Text("$motd")
                }
            }
        }
    }
}