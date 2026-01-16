package com.epn.projectconectatour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Cargar el fragmento de inicio por defecto al abrir la app
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Configurar los clics del menú inferior
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_near_me -> {
                    loadFragment(NearMeFragment())
                    true
                }
                R.id.navigation_guides -> {
                    // loadFragment(GuidesFragment()) // Fragmento de Guias
                    true
                }
                R.id.navigation_about -> {
                    // loadFragment(AboutFragment()) // Fragmento de Sobre Conecta Tour
                    true
                }
                else -> false
            }
        }
    }

    // Función auxiliar para cambiar fragmentos
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}