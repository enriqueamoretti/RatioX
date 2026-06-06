package dev.eamoretti.ratiox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.eamoretti.ratiox.presentation.navigation.AppNavigation
import dev.eamoretti.ratiox.ui.theme.RatioXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RatioXTheme(dynamicColor = false) {
                AppNavigation()
            }
        }
    }
}
