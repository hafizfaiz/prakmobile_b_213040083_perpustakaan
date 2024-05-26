package id.ac.unpas.perpustakaan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.ac.unpas.perpustakaan.ui.screens.MainScreen
import id.ac.unpas.perpustakaan.ui.theme.PerpustakaanTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerpustakaanTheme {
                MainScreen(onExitClick = {
                    finish()
                })
            }
        }
    }
}