package com.epn.projectconectatour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.epn.projectconectatour.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    // _binding es la conexión directa con tu diseño XML (fragment_profile.xml)
    // Nos evita tener que usar "findViewById" a cada rato.
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflamos el diseño: convertimos el XML en algo que Android puede mostrar
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aquí es donde "inicializamos" la pantalla
        setupListeners()
        cargarDatosDePrueba() // TODO: Borrar esto cuando conectemos la API
    }

    private fun setupListeners() {
        // Configurar qué pasa al dar clic en "Guardar"
        binding.btnSave.setOnClickListener {
            if (validarCampos()) {
                val nombre = binding.etName.text.toString()

                // AQUÍ irá la conexión con la API más adelante
                Toast.makeText(requireContext(), "Guardando datos de $nombre...", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar clic en la foto
        binding.imgProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Abrir galería...", Toast.LENGTH_SHORT).show()
        }
    }

    // Una validación básica para que no envíen datos vacíos
    private fun validarCampos(): Boolean {
        if (binding.etName.text.isNullOrEmpty()) {
            binding.etName.error = "El nombre es obligatorio"
            return false
        }
        if (binding.etPhone.text.isNullOrEmpty()) {
            binding.etPhone.error = "El celular es obligatorio"
            return false
        }
        return true
    }

    // Función temporal para que veas cómo se ve con datos rellenos
    private fun cargarDatosDePrueba() {
        binding.etName.setText("Martín Ejemplo")
        binding.etEmail.setText("martin@tour.com")
        binding.etNationality.setText("Ecuador")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiamos la memoria al cerrar la pantalla
        _binding = null
    }
}