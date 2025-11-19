package com.epn.projectconectatour

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.CheckBox

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // Inicializamos Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        // Referencias a las vistas
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val termsCheckBox = findViewById<CheckBox>(R.id.termsCheckBox)
        val createAccountButton = findViewById<Button>(R.id.createAccountButton)
        val loginTextView = findViewById<TextView>(R.id.loginTextView)

        // Configurar el botón de Crear Cuenta
        createAccountButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!termsCheckBox.isChecked) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamamos a la función de registro
            registerUser(email, password, name, phone)
        }

        // Configurar el botón de "Iniciar Sesión" para volver atrás
        loginTextView.setOnClickListener {
            finish()
        }
    }

    private fun registerUser(email: String, password: String, name: String, phone: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Usuario creado en Auth, y guardamos datos extra en Firestore
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val userMap = hashMapOf(
                            "name" to name,
                            "phone" to phone,
                            "email" to email
                        )

                        db.collection("users").document(userId)
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                // Redirigir al Home
                                val intent = Intent(this, Home::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error al guardar datos: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                    }
                } else {
                    Toast.makeText(this, "Registro fallido: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }

    }
}