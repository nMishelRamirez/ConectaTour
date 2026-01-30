package com.epn.projectconectatour

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.AtractivoHome
import com.epn.projectconectatour.network.models.CategoriaHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.SearchView
import kotlin.collections.component2
import kotlin.collections.groupBy
import kotlin.collections.orEmpty

class HomeFragment : Fragment() {

    //private val atractivos = mutableListOf<AtractivoHome>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private lateinit var searchView: SearchView
    private val categorias = mutableListOf<CategoriaHome>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.sitesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryAdapter(categorias)
        recyclerView.adapter = adapter
        searchView = view.findViewById(R.id.searchView)

        // Cargar los datos
        cargarAtractivos()
        // Configuramos la búsqueda
        configurarBusqueda()

    }

    // LLAMADA A LA API
    private fun cargarAtractivos() {
        RetrofitClient.atractivosApi.getAtractivosHome()
            .enqueue(object : Callback<List<AtractivoHome>> {

                override fun onResponse(
                    call: Call<List<AtractivoHome>>,
                    response: Response<List<AtractivoHome>>
                ) {
                    if (response.isSuccessful) {
                        val lista = response.body().orEmpty()

                        val agrupados = lista
                            .groupBy { it.categoria }
                            .map { (categoria, atractivos) ->
                                CategoriaHome(
                                    categoria = categoria,
                                    atractivos = atractivos.take(10)
                                )
                            }

                        categorias.clear()
                        categorias.addAll(agrupados)
                        recyclerView.adapter?.notifyDataSetChanged()
                        recyclerView.scrollToPosition(0)
                    }
                }

                override fun onFailure(call: Call<List<AtractivoHome>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

    // ================= BUSQUEDA =================
    private fun buscarAtractivos(texto: String) {
        Log.d("Busqueda", "Buscando atractivos con el texto: $texto") // Log de lo que se está buscando

        RetrofitClient.atractivosApi.buscarAtractivos(texto)
            .enqueue(object : Callback<List<AtractivoHome>> {

                override fun onResponse(
                    call: Call<List<AtractivoHome>>,
                    response: Response<List<AtractivoHome>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Busqueda", "Resultados obtenidos: ${response.body()}")
                        procesarYMostrar(response.body().orEmpty())
                    } else {
                        Log.e("Busqueda", "Error en la respuesta: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<AtractivoHome>>, t: Throwable) {
                    // Verificación de error en la solicitud
                    Log.e("Busqueda", "Error al hacer la solicitud: ${t.message}")
                    t.printStackTrace()
                }
            })
    }

    // ================= MOSTRAR =================
    private fun procesarYMostrar(lista: List<AtractivoHome>) {
        // Adaptador inicializado
        if (!::adapter.isInitialized) {
            Log.e("HomeFragment", "El adaptador no está inicializado correctamente")
            return
        }
        // Filtrar los atractivos en categorías
        val agrupados = lista
            .groupBy { it.categoria }
            .map { (categoria, atractivos) ->
                CategoriaHome(
                    categoria = categoria,
                    atractivos = atractivos
                )
            }
        // Actualizamos la lista de categorías en el adaptador
        adapter.actualizarCategorias(agrupados)
    }


    // ================= SEARCHVIEW =================
    private fun configurarBusqueda() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                manejarBusqueda(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    Log.d("Busqueda", "El campo de búsqueda está vacío. Recargando los atractivos.")
                    cargarAtractivos()
                } else {
                    Log.d("Busqueda", "Buscando atractivos con el texto: $newText")
                    manejarBusqueda(newText)

                }
                return true
            }

            private fun manejarBusqueda(texto: String?) {
                if (texto.isNullOrBlank()) {
                    cargarAtractivos()
                } else {
                    buscarAtractivos(texto)
                }
            }
        })
    }

    // ADAPTER
    inner class SitesAdapter(private val siteList: List<AtractivoHome>) :
        RecyclerView.Adapter<SitesAdapter.SiteViewHolder>() {

        inner class SiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.siteTitleTextView)
            val categoryTextView: TextView = itemView.findViewById(R.id.siteCategoryTextView)
            val imageView: ImageView = itemView.findViewById(R.id.siteImageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_site, parent, false)
            return SiteViewHolder(view)
        }

        override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
            val site = siteList[position]

            holder.titleTextView.text = site.nombre
            holder.categoryTextView.text = site.categoria

            Glide.with(holder.itemView.context)
                .load(site.imagenPrincipal)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.imageView)

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, SiteDetailActivity::class.java)
                intent.putExtra("id", site.id) // SOLO ID
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = siteList.size
    }
}