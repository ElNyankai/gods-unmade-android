package com.sixseven.godsunmade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sixseven.godsunmade.ui.navigation.AppNavHost
import com.sixseven.godsunmade.ui.theme.GodsUnmadeTheme

// la unica pantalla de la app, aqui arranca todo
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // dibuja el tema y la navegacion con Compose
        setContent {
            GodsUnmadeTheme { AppNavHost() }
        }
    }
}
