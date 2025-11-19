package com.epn.projectconectatour

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Inicializamos Firebase Auth
        auth = FirebaseAuth.getInstance()

        // variables
        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        val registerTextView = findViewById<TextView>(R.id.registerTextView)

        // Configurar el botón de login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Por favor, ingresa tu email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        // CONEXIÓN A OLVIDÉ CONTRASEÑA - Navegar a la pantalla de Olvido contraseña
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        // CONEXIÓN A REGISTRO - Navegar a la pantalla de registro
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    // Función para iniciar sesión
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Si el login es exitoso, ir a la pantalla principal
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    // Redirigir a la pagina inicial
                    startActivity(Intent(this, Home::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login fallido: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}