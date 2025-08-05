package com.example.a08_modulo_a2_05.views

import android.R.attr.rating
import android.annotation.SuppressLint
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a08_modulo_a2_05.BaseScaffold
import com.example.a08_modulo_a2_05.Escola
import com.example.a08_modulo_a2_05.SchoolListResponse
import com.example.a08_modulo_a2_05.network.API
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EscolasScreen(nav: NavHostController) {
    val scope = rememberCoroutineScope()
    var escolas by remember { mutableStateOf<List<Escola>?>(null) }
    BaseScaffold(nav, "Lista de escolas") { paddingValues ->
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            scope.launch {
                try {
                    val resp: SchoolListResponse = API.service.getEscolas()
                    escolas = resp.escolas.sortedBy { it.nome.lowercase() }
                } catch (e: Exception) {}
            }
        }

        if (escolas != null) {
            LazyColumn (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp,
                Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally){
                items(escolas!!) {
                    Text(it.nome)
                    Text("${((it.avaliacao)*100).toInt()}%")

                }
            }
        }

    }
}
