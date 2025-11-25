package com.epn.projectconectatour

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

class NearMeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_near_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. NAVEGABILIDAD (Botón Atrás del Fragmento)
        val backButton = view.findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            // Regresar a la pantalla anterior (generalmente Home)
            parentFragmentManager.popBackStack()
            // O si estás en el HomeActivity y quieres ir al tab de inicio:
            // (activity as? HomeActivity)?.navigateToHome()
        }

        // Referencias a los contenedores
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val mapContainer = view.findViewById<View>(R.id.mapContainer)
        val destinationsContainer = view.findViewById<View>(R.id.destinationsContainer)

        // Configuración inicial de Tabs
        mapContainer.visibility = View.GONE
        destinationsContainer.visibility = View.VISIBLE
        tabLayout.selectTab(tabLayout.getTabAt(1))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Tab Mapa
                        mapContainer.visibility = View.VISIBLE
                        destinationsContainer.visibility = View.GONE
                    }
                    1 -> { // Tab Destinos
                        mapContainer.visibility = View.GONE
                        destinationsContainer.visibility = View.VISIBLE
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Configurar RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.destinationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // 2. DATOS (Usando la nueva clase Site)
        val destinations = listOf(
            Site(
                title = "Basílica del Voto Nacional",
                description = "La Basílica del Voto Nacional es la obra más importante de la arquitectura neogótica ecuatoriana y una de las más representativas del continente americano.",
                imageUrl = "https://imgs.search.brave.com/rmjs5veGgi4uWeBIs5whKmu4GJWG9tBys75yg10zkZ0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9sb3N0/cmF2ZWxlcm9zLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAy/My8wMi9Db25kb3It/VmlzdGEtODAweDUz/Mi5qcGc",
                category = "Iglesia"
            ),
            Site(
                title = "Centro Histórico de Quito",
                description = "El centro histórico de Quito es el mejor conservado y uno de los más importantes de América Latina.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Plaza_de_la_Independencia_de_Quito.jpg/280px-Plaza_de_la_Independencia_de_Quito.jpg",
                category = "Histórico"
            ),
            Site(
                title = "Iglesia de San Francisco",
                description = "La Iglesia de San Francisco es una basílica católica que se levanta en medio del centro histórico de Quito.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Iglesia_de_San_Francisco%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_142.JPG/280px-Iglesia_de_San_Francisco%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_142.JPG",
                category = "Religioso"
            )
        )

        recyclerView.adapter = DestinationsAdapter(destinations)
    }

    // Adaptador actualizado para usar 'Site'
    inner class DestinationsAdapter(private val sites: List<Site>) :
        RecyclerView.Adapter<DestinationsAdapter.DestinationViewHolder>() {

        inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.destinationNameTextView)
            val imageView: ImageView = itemView.findViewById(R.id.destinationImageView)
            val detailsButton: MaterialButton = itemView.findViewById(R.id.detailsButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_destination, parent, false)
            return DestinationViewHolder(view)
        }

        override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
            val site = sites[position]
            holder.nameTextView.text = site.title

            Glide.with(holder.itemView.context)
                .load(site.imageUrl)
                .circleCrop()
                .into(holder.imageView)

            // 3. NAVEGACIÓN A DETALLES
            holder.detailsButton.setOnClickListener {
                // Creamos el Intent hacia la Activity de detalles
                val intent = Intent(holder.itemView.context, SiteDetailActivity::class.java)

                // Pasamos el objeto 'site' completo
                intent.putExtra("SITE_DATA", site)

                // Iniciamos la actividad
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = sites.size
    }
}