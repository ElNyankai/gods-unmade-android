package com.sixseven.godsunmade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sixseven.godsunmade.ui.navigation.AppNavHost
import com.sixseven.godsunmade.ui.theme.GodsUnmadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GodsUnmadeTheme { AppNavHost() }
        }
    }
}
