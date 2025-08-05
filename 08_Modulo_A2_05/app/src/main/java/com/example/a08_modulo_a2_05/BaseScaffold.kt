package com.example.a08_modulo_a2_05

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.a08_modulo_a2_05.network.API

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScaffold(nav: NavHostController, title: String = "", content: @Composable (content: PaddingValues) -> Unit) {

    val contexto = LocalContext.current
    val prefs = contexto.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
    val token = prefs.getString("jwt_token", "")
    var loading by remember { mutableStateOf(true) }
    var showModal by remember { mutableStateOf(false) }
    var valid by remember { mutableStateOf(false) }

    LaunchedEffect (Unit){
        try {
            valid = API.service.validarToken(puxarToken(token.toString())).valid
            showModal = !valid
        } catch (e: Exception) {
            null
        } finally {
            loading = false
        }
    }

    when {
        loading -> {

        }
        showModal -> {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Sessão expirou") },
                text = { Text("Por favor, faça login novamente.") },
                confirmButton = {
                    TextButton(onClick = {
                        prefs.edit().clear().apply()
                        nav.navigate("login")
                    }) {
                        Text("OK")
                    }
                }
            )
        }
        else -> {
        }
    }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    )
    { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content(paddingValues)
        }
    }
}