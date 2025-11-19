package com.epn.projectconectatour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.widget.Button
import android.widget.EditText

class ForgotPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Referencias a la vista
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Botón Enviar
        sendButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sendPasswordResetEmail(email)
        }

        // Configurar botón Cancelar
        cancelButton.setOnClickListener {
            finish() // Cierra la actividad y vuelve al login
        }
    }

    // Función para recuperar la contraseña
    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Correo enviado. Revisa tu bandeja.", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al enviar correo: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }

    }

}