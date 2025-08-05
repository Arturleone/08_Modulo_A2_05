package com.example.a08_modulo_a2_05.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.a08_modulo_a2_05.BaseScaffold
import com.example.a08_modulo_a2_05.Escola
import com.example.a08_modulo_a2_05.SchoolListResponse
import com.example.a08_modulo_a2_05.network.API
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MapaScreen(nav: NavHostController) {
    val scope = rememberCoroutineScope()
    var escolas by remember { mutableStateOf<List<Escola>?>(emptyList()) }
    var selectedSchool by remember { mutableStateOf<Escola?>(null) }
    var position = LatLng(-15.738387121157935, -47.92534498704637)
    val cameraPosition = rememberCameraPositionState()
    cameraPosition.position = CameraPosition.fromLatLngZoom(position, 15f)
    LaunchedEffect(Unit) {
        try {
            val resp: SchoolListResponse = API.service.getEscolas()
            escolas = resp.escolas.sortedBy { it.nome.lowercase() }
        } catch (e: Exception) {}
    }

    BaseScaffold(nav, "Mapa Screen") { content ->
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPosition
        ) {
            escolas!!.forEach { escola ->
                val pos = LatLng(escola.latitude, escola.longitude)
                Marker(
                    state = MarkerState(position = pos),
                    title = escola.nome,
                    snippet = "Avaliação: ${"%.1f".format(escola.avaliacao)}",
                    icon = if (escola == selectedSchool)
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                    else
                        BitmapDescriptorFactory.defaultMarker(),
                    onClick = {
                        selectedSchool = escola
                        true
                    }
                )
            }
        }
    }

    selectedSchool?.let {
        AlertDialog(
            title = {
                it.nome
            },
            text = {"${it.nome}, ${it.avaliacao}"},
            onDismissRequest = { selectedSchool =null },
            confirmButton = {selectedSchool = null}
        )
    }
}