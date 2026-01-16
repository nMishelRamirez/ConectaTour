package com.epn.projectconectatour

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.CheckBox
import androidx.lifecycle.lifecycleScope
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.RegisterRequest
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
        lifecycleScope.launch {
            try {
                val request = RegisterRequest(
                    nombre = name,
                    celular = phone,
                    correo = email,
                    contraseña = password
                )

                val response = RetrofitClient.apiService.register(request)

                if (response.isSuccessful && response.body()?.estado == "Ok") {
                    val turista = response.body()?.datos
                    Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()

                    // Guardar datos del usuario en SharedPreferences
                    val sharedPref = getSharedPreferences("ConectaTourPrefs", MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putInt("userId", turista?.id ?: 0)
                        putString("userName", turista?.nombre)
                        putString("userEmail", turista?.correo)
                        apply()
                    }

                    // Redirigir al Home
                    val intent = Intent(this@RegisterActivity, Home::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorMsg = response.body()?.mensaje ?: "Error desconocido"
                    Toast.makeText(this@RegisterActivity, errorMsg, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}