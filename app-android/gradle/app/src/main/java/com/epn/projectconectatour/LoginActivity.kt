package com.epn.projectconectatour

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.LoginRequest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // variables
        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        val registerTextView = findViewById<TextView>(R.id.registerTextView)

        // Configurar el botón de login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Por favor, ingresa tu email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        // CONEXIÓN A OLVIDÉ CONTRASEÑA
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        // CONEXIÓN A REGISTRO
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Función para iniciar sesión
    private fun loginUser(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val request = LoginRequest(
                    correo = email,
                    contraseña = password
                )

                val response = RetrofitClient.apiService.login(request)

                if (response.isSuccessful && response.body()?.estado == "Ok") {
                    val turista = response.body()?.datos
                    Toast.makeText(this@LoginActivity, "Login exitoso", Toast.LENGTH_SHORT).show()

                    // Guardar datos del usuario en SharedPreferences
                    val sharedPref = getSharedPreferences("ConectaTourPrefs", MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putInt("userId", turista?.id ?: 0)
                        putString("userName", turista?.nombre)
                        putString("userEmail", turista?.correo)
                        apply()
                    }

                    // Redirigir a la pagina inicial
                    startActivity(Intent(this@LoginActivity, Home::class.java))
                    finish()
                } else {
                    val errorMsg = response.body()?.mensaje ?: "Credenciales incorrectas"
                    Toast.makeText(this@LoginActivity, errorMsg, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}