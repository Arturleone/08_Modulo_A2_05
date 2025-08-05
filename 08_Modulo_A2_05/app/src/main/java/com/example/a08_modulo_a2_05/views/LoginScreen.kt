package com.example.a08_modulo_a2_05.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import coil.network.HttpException
import com.example.a08_modulo_a2_05.AuthRequest
import com.example.a08_modulo_a2_05.network.API
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@Composable
fun LoginScreen(nav: NavHostController) {

    val ctx = LocalContext.current
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var manterConectado by remember { mutableStateOf(false) }
    val Toast: (String) -> Unit = {
        Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
    }
    val scope = rememberCoroutineScope()

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(6.dp,
        Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(
            value = email.value,
            onValueChange = {email.value = it},
            label = {"Email"}
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = {password.value = it},
            label = {"Senha"}
        )

        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Checkbox(
                checked = manterConectado,
                onCheckedChange = {manterConectado = !manterConectado}
            )

            Text("mantenha-me conectado")
        }

        Button(onClick = {
            if (!email.value.contains(".") || !email.value.contains("@")) {
                Toast("Erro no Email, Precisa conter '@' ou '.'")
                return@Button
            }
            if (!password.value.any{it.isDigit()} || !password.value.any{it.isLetter()} || password.value.length <= 6) {
                Toast("Erro na Senha, Precisa conter no mínimo 6 caracteres, uma letra e um dígito")
                return@Button
            }

            scope.launch {
                try {
                    val token = API.service.gerarToken(login = AuthRequest(email.value, password.value)).token
                    val prefs = ctx.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
                    prefs.edit().apply{
                        putString("jwt_token", token)
                        putString("email", email.value)
                        apply()
                    }
                    nav.navigate("home")
                } catch (e: Exception) {
                    val generalError = when(e) {
                        is UnknownHostException -> Toast("Sem Conexão com a internet")
                        is HttpException -> { Toast("Email ou Senha Incorretos") }
                        else -> Toast("Erro inesperado")
                    }

                }
            }
        }) {
            Text("Entrar")
        }
    }
}