package com.example.a08_modulo_a2_05

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a08_modulo_a2_05.ui.theme._08_Modulo_A2_05Theme
import com.example.a08_modulo_a2_05.views.EscolasScreen
import com.example.a08_modulo_a2_05.views.HomeScreen
import com.example.a08_modulo_a2_05.views.LoginScreen
import com.example.a08_modulo_a2_05.views.MapaScreen
import com.example.a08_modulo_a2_05.views.SplashScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        enableEdgeToEdge()
        setContent {
            _08_Modulo_A2_05Theme(darkTheme = false) {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val nav = rememberNavController()
    val scope = rememberCoroutineScope ()
    val drawerState = rememberDrawerState (DrawerValue.Closed)
    val ctx = LocalContext.current
    val telaAtual = nav.currentBackStackEntryAsState().value?.destination?.route
    if (telaAtual != "mapa") {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(nav) { it ->
                    when (it) {
                        "logout" -> {
                            val prefs = ctx.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
                            prefs.edit().apply{
                                putString("jwt_token", "")
                                apply()
                            }
                            scope.launch { drawerState.close() }
                            nav.navigate("login")
                        }
                        else -> {
                            scope.launch { drawerState.close() }
                            nav.navigate(it)
                        }
                    }

                }
            }
        ) {
            NavApp(nav, scope, drawerState)
        }
    } else {
        NavApp(nav, scope, drawerState)
    }

}

@Composable
fun NavApp(nav: NavHostController, scope: CoroutineScope, drawerState: DrawerState) {
    NavHost(nav, "splash") {
        composable("splash") {
            SplashScreen(nav)
        }
        composable("home") {
            HomeScreen(nav)
        }
        composable("login") {
            LoginScreen(nav)
        }
        composable("escolas") {
            EscolasScreen(nav)
        }
        composable("mapa") {
            MapaScreen(nav)
        }
    }
}

