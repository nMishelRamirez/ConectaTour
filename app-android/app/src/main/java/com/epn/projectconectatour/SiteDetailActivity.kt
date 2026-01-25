package com.epn.projectconectatour

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.AtractivoDetalle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SiteDetailActivity : AppCompatActivity() {
    // Datos del sitio
    private var titleText: String = ""
    private var descriptionText: String = ""
    private var infoText: String = ""
    private var imageUrl: String? = null

    // Vistas
    private lateinit var detailTitleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var detailImageView: ImageView
    private lateinit var tabAboutContainer: LinearLayout
    private lateinit var tabInfoContainer: LinearLayout
    private lateinit var tabAboutText: TextView
    private lateinit var tabInfoText: TextView
    private lateinit var tabAboutIndicator: View
    private lateinit var tabInfoIndicator: View
    private lateinit var backButton: ImageView
    private lateinit var ivRouteMap: ImageView // para el mapa si se usa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        // VINCULAR VISTAS
        detailTitleTextView = findViewById(R.id.detailTitleTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        detailImageView = findViewById(R.id.detailImageView)
        tabAboutContainer = findViewById(R.id.tabAboutContainer)
        tabInfoContainer = findViewById(R.id.tabInfoContainer)
        tabAboutText = findViewById(R.id.tabAboutText)
        tabInfoText = findViewById(R.id.tabInfoText)
        tabAboutIndicator = findViewById(R.id.tabAboutIndicator)
        tabInfoIndicator = findViewById(R.id.tabInfoIndicator)
        backButton = findViewById(R.id.backButton)
        ivRouteMap = findViewById(R.id.ivRouteMap)

        // OBTENER ID DEL SITIO
        val id = intent.getIntExtra("id", 0)
        if (id == 0) {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // LLAMADA A LA API PARA DETALLE
        RetrofitClient.atractivosApi.getAtractivoDetalle(id)
            .enqueue(object : Callback<AtractivoDetalle> {
                override fun onResponse(
                    call: Call<AtractivoDetalle>,
                    response: Response<AtractivoDetalle>
                ) {
                    val detalle = response.body()
                    if (detalle == null || detalle.informacionGeneral == null) {
                        Toast.makeText(
                            this@SiteDetailActivity,
                            "No se pudo cargar el detalle",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    // ASIGNAR DATOS
                    titleText = detalle.informacionGeneral.nombre ?: "Sin nombre"
                    descriptionText = detalle.informacionGeneral.descripcion ?: "Sin descripción"
                    infoText = """
                        📍 Dirección: ${detalle.informacionGeneral.direccion ?: "No disponible"}
                        ⏰ Horario: ${detalle.informacionAdicional?.horario ?: "No disponible"}
                        💰 Precio: ${detalle.informacionAdicional?.precioEntrada ?: "No disponible"}
                        📸 Actividades: ${detalle.informacionAdicional?.actividades ?: "No disponible"}
                    """.trimIndent()
                    imageUrl = detalle.imagenPrincipal

                    // CARGAR UI
                    detailTitleTextView.text = titleText
                    descriptionTextView.text = descriptionText
                    Glide.with(this@SiteDetailActivity)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(detailImageView)

                    // PESTAÑA ABOUT POR DEFECTO
                    mostrarAbout()
                }

                override fun onFailure(call: Call<AtractivoDetalle>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        this@SiteDetailActivity,
                        "Error al cargar detalle",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        // INTERACCIÓN DE PESTAÑAS
        tabAboutContainer.setOnClickListener { mostrarAbout() }
        tabInfoContainer.setOnClickListener { mostrarInfo() }

        // BOTÓN ATRÁS
        backButton.setOnClickListener { finish() }
    }

    private fun mostrarAbout() {
        tabAboutText.setTextColor(Color.parseColor("#1A2E35"))
        tabAboutText.setTypeface(null, Typeface.BOLD)
        tabAboutIndicator.visibility = View.VISIBLE

        tabInfoText.setTextColor(Color.parseColor("#757575"))
        tabInfoText.setTypeface(null, Typeface.NORMAL)
        tabInfoIndicator.visibility = View.INVISIBLE

        descriptionTextView.text = descriptionText
    }

    private fun mostrarInfo() {
        tabAboutText.setTextColor(Color.parseColor("#757575"))
        tabAboutText.setTypeface(null, Typeface.NORMAL)
        tabAboutIndicator.visibility = View.INVISIBLE

        tabInfoText.setTextColor(Color.parseColor("#1A2E35"))
        tabInfoText.setTypeface(null, Typeface.BOLD)
        tabInfoIndicator.visibility = View.VISIBLE

        descriptionTextView.text = infoText
    }
}
