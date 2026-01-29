package com.epn.projectconectatour

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.epn.projectconectatour.databinding.FragmentProfileBinding
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.Turista
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        val sharedPref = requireActivity().getSharedPreferences("ConectaTourPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", 0)

        if (userId != 0) {
            cargarDatosLocales(sharedPref)
        }

        binding.btnEdit.setOnClickListener { toggleEditMode(true) }
        binding.btnSave.setOnClickListener { updateProfile(userId) }
    }

    private fun cargarDatosLocales(sharedPref: android.content.SharedPreferences) {
        binding.etNombre.setText(sharedPref.getString("userName", ""))
        binding.etCelular.setText(sharedPref.getString("userPhone", ""))
        binding.etCorreo.setText(sharedPref.getString("userEmail", ""))
        binding.etContrasena.setText(sharedPref.getString("userPassword", ""))

        // El correo no es editable
        binding.etCorreo.isEnabled = false
    }

    private fun toggleEditMode(isEditing: Boolean) {
        binding.etNombre.isEnabled = isEditing
        binding.etCelular.isEnabled = isEditing
        binding.etContrasena.isEnabled = isEditing

        binding.btnSave.visibility = if (isEditing) View.VISIBLE else View.GONE
        binding.btnEdit.visibility = if (isEditing) View.GONE else View.VISIBLE
    }

    private fun updateProfile(id: Int) {
        val updatedTurista = Turista(
            id = id,
            nombre = binding.etNombre.text.toString(),
            celular = binding.etCelular.text.toString(),
            correo = binding.etCorreo.text.toString(),
            contraseña = binding.etContrasena.text.toString()
        )

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.apiService.updateTurista(id, updatedTurista)

                if (response.isSuccessful) {
                    val apiResponse = response.body()

                    if (apiResponse?.estado == "Ok") {
                        Toast.makeText(requireContext(), "¡Perfil Actualizado!", Toast.LENGTH_SHORT).show()

                        // CORRECCIÓN AQUÍ:
                        // Extraemos el objeto Turista que viene dentro de 'datos'
                        val turistaConfirmado = apiResponse.datos ?: updatedTurista

                        actualizarPreferencias(turistaConfirmado)
                        toggleEditMode(false)
                    } else {
                        Toast.makeText(requireContext(), apiResponse?.mensaje ?: "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarPreferencias(turista: Turista) {
        val sharedPref = requireActivity().getSharedPreferences("ConectaTourPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("userName", turista.nombre)
            putString("userPhone", turista.celular)
            putString("userPassword", turista.contraseña)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}