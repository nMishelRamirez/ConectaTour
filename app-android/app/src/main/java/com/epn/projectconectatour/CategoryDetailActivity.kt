package com.epn.projectconectatour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.AtractivoHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ImageView

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeFragment.SitesAdapter
    private lateinit var backButton: ImageView
    private val atractivos = mutableListOf<AtractivoHome>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_category_home)

        backButton = findViewById(R.id.backButton)
        recyclerView = findViewById(R.id.horizontalRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = HomeFragment().SitesAdapter(atractivos)
        recyclerView.adapter = adapter

        // Botón de regresar
        backButton.setOnClickListener {
            finish() // Regresa a la actividad anterior
        }

        // Recibir el nombre de la categoría
        val categoria = intent.getStringExtra("categoria") ?: ""
        cargarAtractivos(categoria)

    }

    private fun cargarAtractivos(categoria: String) {
        RetrofitClient.atractivosApi.getAtractivosHome()
            .enqueue(object : Callback<List<AtractivoHome>> {
                override fun onResponse(
                    call: Call<List<AtractivoHome>>,
                    response: Response<List<AtractivoHome>>
                ) {
                    if (response.isSuccessful) {
                        atractivos.clear()
                        atractivos.addAll(response.body().orEmpty().filter { it.categoria == categoria })
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<AtractivoHome>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}
