package com.epn.projectconectatour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // Referencias a los contenedores
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val mapContainer = view.findViewById<View>(R.id.mapContainer)
        val destinationsContainer = view.findViewById<View>(R.id.destinationsContainer)

        // Por defecto mostrar la vista de destinos (tab 1)
        mapContainer.visibility = View.GONE
        destinationsContainer.visibility = View.VISIBLE
        tabLayout.selectTab(tabLayout.getTabAt(1)) // Seleccionar "Destinos" por defecto

        // Listener para cambiar entre tabs
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        // Tab "Mapa" - Mostrar solo el mapa grande
                        mapContainer.visibility = View.VISIBLE
                        destinationsContainer.visibility = View.GONE
                    }
                    1 -> {
                        // Tab "Destinos" - Mostrar mapa pequeño + lista
                        mapContainer.visibility = View.GONE
                        destinationsContainer.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Configurar RecyclerView de destinos
        val recyclerView = view.findViewById<RecyclerView>(R.id.destinationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Datos quemados de destinos cercanos
        val destinations = listOf(
            Destination(
                "Basílica Católica Nuestra Señora de La Merced | Quito",
                "https://imgs.search.brave.com/rmjs5veGgi4uWeBIs5whKmu4GJWG9tBys75yg10zkZ0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9sb3N0/cmF2ZWxlcm9zLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAy/My8wMi9Db25kb3It/VmlzdGEtODAweDUz/Mi5qcGc"
            ),
            Destination(
                "Centro Histórico de Quito",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Plaza_de_la_Independencia_de_Quito.jpg/280px-Plaza_de_la_Independencia_de_Quito.jpg"
            ),
            Destination(
                "Iglesia y Convento de San Francisco",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Iglesia_de_San_Francisco%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_142.JPG/280px-Iglesia_de_San_Francisco%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_142.JPG"
            )
        )

        recyclerView.adapter = DestinationsAdapter(destinations)
    }

    // Modelo de datos
    data class Destination(val name: String, val imageUrl: String)

    // Adaptador
    inner class DestinationsAdapter(private val destinations: List<Destination>) :
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
            val destination = destinations[position]
            holder.nameTextView.text = destination.name
            Glide.with(holder.itemView.context)
                .load(destination.imageUrl)
                .circleCrop()
                .into(holder.imageView)

            holder.detailsButton.setOnClickListener {
                android.widget.Toast.makeText(
                    context,
                    "Ver detalles de: ${destination.name}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun getItemCount() = destinations.size
    }
}