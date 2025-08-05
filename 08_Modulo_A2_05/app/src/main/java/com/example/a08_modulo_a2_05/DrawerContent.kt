package com.example.a08_modulo_a2_05

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlin.collections.forEach
import kotlin.to

@Composable
fun DrawerContent(nav: NavHostController,rota: (String) -> Unit) {
    val itens = listOf(
        "Lista de escolas" to "escolas",
        "Mapa" to "mapa",
        "Logout" to "logout",
    )

    val telaAtual = nav.currentBackStackEntryAsState().value?.destination?.route

    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(0.75f).background(Color.White),
        verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }

        Text("Aprender+", fontSize = 30.sp)

        itens.forEach { it ->
            val selecionado = telaAtual == it.second
            Text(
                it.first,
                modifier = Modifier.clickable { rota(it.second) }.fillMaxWidth().background(if(selecionado) Color.LightGray else Color.White),
                fontSize = 20.sp
            )
        }
    }
}
