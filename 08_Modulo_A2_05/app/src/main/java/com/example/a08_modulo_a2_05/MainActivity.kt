package com.example.a08_modulo_a2_05

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a08_modulo_a2_05.ui.theme._08_Modulo_A2_05Theme
import com.example.a08_modulo_a2_05.views.HomeScreen
import com.example.a08_modulo_a2_05.views.LoginScreen
import com.example.a08_modulo_a2_05.views.SplashScreen

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
            _08_Modulo_A2_05Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val nav = rememberNavController()
    NavApp(nav)
}

@Composable
fun NavApp(nav: NavHostController) {
    NavHost(nav, "login") {
        composable("splash") {
            SplashScreen(nav)
        }
        composable("home") {
            HomeScreen(nav)
        }
        composable("login") {
            LoginScreen(nav)
        }
    }
}

