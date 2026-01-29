package com.epn.projectconectatour

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class SiteDetailActivity : AppCompatActivity() {

    // Variables para almacenar los textos que se mostrarán en cada pestaña
    private var descriptionText: String = ""
    private var infoText: String = ""
    private var titleText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        // 1. RECUPERAR DATOS (Intent)
        val nameIntent = intent.getStringExtra("name") ?: ""
        // Recuperamos la URL original, pero la podemos sobrescribir más abajo
        var imageUrl = intent.getStringExtra("imageUrl")

        // 2. VINCULAR VISTAS
        val backButton: ImageView = findViewById(R.id.backButton)
        val detailTitleTextView: TextView = findViewById(R.id.detailTitleTextView)
        val detailImageView: ImageView = findViewById(R.id.detailImageView) // Imagen PRINCIPAL (Arriba)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val ivRouteMap: ImageView = findViewById(R.id.ivRouteMap) // Imagen del MAPA (Abajo)

        // Vistas de las Pestañas (Tabs)
        val tabAboutContainer: LinearLayout = findViewById(R.id.tabAboutContainer)
        val tabInfoContainer: LinearLayout = findViewById(R.id.tabInfoContainer)
        val tabAboutText: TextView = findViewById(R.id.tabAboutText)
        val tabInfoText: TextView = findViewById(R.id.tabInfoText)
        val tabAboutIndicator: View = findViewById(R.id.tabAboutIndicator)
        val tabInfoIndicator: View = findViewById(R.id.tabInfoIndicator)

        // 3. LÓGICA DE CONTENIDO
        var mapResId = R.drawable.placeholder // Recurso para el MAPA (Abajo)

        infoText = "Horarios de atención:\nLunes a Domingo: 09:00 - 17:00\n\nRecomendaciones:\nLlevar ropa cómoda, protector solar, cámara fotográfica y documentos de identificación."

        // Lógica de asignación según el nombre del sitio
        if (nameIntent.contains("Basílica", ignoreCase = true)) {
            titleText = "La Basílica del Voto Nacional"
            descriptionText = "La Basílica del Voto Nacional es la obra más importante de la arquitectura neogótica ecuatoriana y una de las más representativas del continente americano."
            mapResId = R.drawable.mapa_basilica
        }
        else if (nameIntent.contains("San Francisco", ignoreCase = true)) {
            titleText = "Iglesia de San Francisco"
            descriptionText = "La Iglesia de San Francisco es una basílica católica que se levanta en medio del centro histórico de Quito. Es el conjunto arquitectónico de mayor dimensión dentro de los centros históricos de toda América."

            // Asignamos el mapa correspondiente
            mapResId = R.drawable.mapa_sanfrancisco

            // CORRECCIÓN SOLICITADA: Usar esta URL específica para la imagen de referencia
            imageUrl = "https://imgs.search.brave.com/YMr62PzvbNNTxJ4emiiTQWmt4CfF875HIBYA9eIEuC8/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvY29tbW9ucy83/Lzc3L0lnbGVzaWFf/ZGVfU2FuX0ZyYW5j/aXNjbyxfUXVpdG8s/X0VjdWFkb3IsXzIw/MTUtMDctMjIsX0RE/XzE1Mi5KUEc"
        }
        else if (nameIntent.contains("Centro Histórico", ignoreCase = true)) {
            titleText = "Centro Histórico"
            descriptionText = "El Centro Histórico de Quito es el mejor conservado y uno de los más importantes de América Latina. Fue declarado Patrimonio de la Humanidad por la Unesco en 1978."
            mapResId = R.drawable.mapa_centro
        }
        else {
            titleText = nameIntent
            descriptionText = intent.getStringExtra("description") ?: "Información no disponible."
            mapResId = R.drawable.mapa_basilica
        }

        // 4. PINTAR LA INTERFAZ
        detailTitleTextView.text = titleText
        descriptionTextView.text = descriptionText

        // Cargar mapa (Abajo)
        try {
            ivRouteMap.setImageResource(mapResId)
        } catch (e: Exception) {
            ivRouteMap.setImageResource(R.drawable.placeholder)
        }

        // Cargar imagen PRINCIPAL (Arriba) usando Glide con la URL (posiblemente actualizada)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(detailImageView)

        // 5. LÓGICA DE INTERACCIÓN DE PESTAÑAS
        tabAboutContainer.setOnClickListener {
            tabAboutText.setTextColor(Color.parseColor("#1A2E35"))
            tabAboutText.setTypeface(null, Typeface.BOLD)
            tabAboutIndicator.visibility = View.VISIBLE

            tabInfoText.setTextColor(Color.parseColor("#757575"))
            tabInfoText.setTypeface(null, Typeface.NORMAL)
            tabInfoIndicator.visibility = View.INVISIBLE

            descriptionTextView.text = descriptionText
        }

        tabInfoContainer.setOnClickListener {
            tabAboutText.setTextColor(Color.parseColor("#757575"))
            tabAboutText.setTypeface(null, Typeface.NORMAL)
            tabAboutIndicator.visibility = View.INVISIBLE

            tabInfoText.setTextColor(Color.parseColor("#1A2E35"))
            tabInfoText.setTypeface(null, Typeface.BOLD)
            tabInfoIndicator.visibility = View.VISIBLE

            descriptionTextView.text = infoText
        }

        // 6. BOTÓN ATRÁS
        backButton.setOnClickListener {
            finish()
        }
    }
}