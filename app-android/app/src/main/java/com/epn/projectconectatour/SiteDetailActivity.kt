package com.epn.projectconectatour

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.geometry.Size
import com.bumptech.glide.Glide

class SiteDetailActivity : AppCompatActivity() {

    private lateinit var descriptionTextView: TextView
    private var fullDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        // RECUPERAR DATOS
        val site = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("SITE_DATA", Site::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("SITE_DATA") as? Site
        }

        // Referencias UI
        val backButton = findViewById<ImageView>(R.id.backButton)
        val titleTextView = findViewById<TextView>(R.id.detailTitleTextView)
        val imageView = findViewById<ImageView>(R.id.detailImageView)
        descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        // Tabs UI
        val tabAboutContainer = findViewById<LinearLayout>(R.id.tabAboutContainer)
        val tabInfoContainer = findViewById<LinearLayout>(R.id.tabInfoContainer)
        val tabAboutText = findViewById<TextView>(R.id.tabAboutText)
        val tabInfoText = findViewById<TextView>(R.id.tabInfoText)
        val tabAboutIndicator = findViewById<View>(R.id.tabAboutIndicator)
        val tabInfoIndicator = findViewById<View>(R.id.tabInfoIndicator)

        // MOSTRAR DATOS
        if (site != null) {
            titleTextView.text = site.title
            fullDescription = site.description
            descriptionTextView.text = fullDescription

            // Cargar imagen
            Glide.with(this).load(site.imageUrl).into(imageView)
        }

        // NAVEGABILIDAD
        //backButton.setOnClickListener {
            //finish()
        //}

        // Lógica de las Pestañas
        tabAboutContainer.setOnClickListener {
            tabAboutText.setTextColor(Color.parseColor("#1A2E35"))
            tabAboutText.typeface = android.graphics.Typeface.DEFAULT_BOLD
            tabAboutIndicator.visibility = View.VISIBLE

            tabInfoText.setTextColor(Color.parseColor("#757575"))
            tabInfoText.typeface = android.graphics.Typeface.DEFAULT
            tabInfoIndicator.visibility = View.INVISIBLE

            descriptionTextView.text = fullDescription
        }

        tabInfoContainer.setOnClickListener {
            tabAboutText.setTextColor(Color.parseColor("#757575"))
            tabAboutText.typeface = android.graphics.Typeface.DEFAULT
            tabAboutIndicator.visibility = View.INVISIBLE

            tabInfoText.setTextColor(Color.parseColor("#1A2E35"))
            tabInfoText.typeface = android.graphics.Typeface.DEFAULT_BOLD
            tabInfoIndicator.visibility = View.VISIBLE

            descriptionTextView.text = "Horarios de atención:\nLunes a Viernes: 9:00 AM - 5:00 PM\nSábados: 10:00 AM - 2:00 PM\n\nCosto de entrada:\nAdultos: $2.00\nNiños: $1.00"
        }
    }
}