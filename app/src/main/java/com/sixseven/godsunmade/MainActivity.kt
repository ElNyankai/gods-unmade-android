package com.sixseven.godsunmade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sixseven.godsunmade.ui.navigation.AppNavHost
import com.sixseven.godsunmade.ui.theme.GodsUnmadeTheme

/** Activity única: aplica el tema y monta la navegación en Compose. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GodsUnmadeTheme { AppNavHost() }
        }
    }
}
