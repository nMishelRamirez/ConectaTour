package com.epn.projectconectatour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.coroutines.*

/**
 * Esta es la Activity LANZADORA (Launcher).
 * Su única responsabilidad es lanzar un Screem con
 * el logo de la pagina y luego diriguirse al Login.
 */

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Instalar la Splash Screen
        val splashScreen = installSplashScreen()

        // Llamar a una corutina para esperar 2 segundos y luego navegar al Login
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000) // Espera 2 segundos

            // Después de 2 segundos, navega a LoginActivity
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad de Splash
        }
    }
}