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

class NearMeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_near_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                "https://imgs.search.brave.com/a1sVxbMeP9CU8Fc1Q_75PhKNwwXUdpAuqZkmebUYMMA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly92aXN0/YWhlcm1vc2EuZWMv/d3AtY29udGVudC91/cGxvYWRzLzIwMTkv/MDMvY2VudHJvLWhp/c3Rvcmljby1xdWl0/by12aXN0YS1oZXJt/b3NhLmpwZw"
            ),
            Destination(
                "Mitad del Mundo",
                "https://imgs.search.brave.com/-Jz5QNxickBGTlglCVpNAMH3x9sZ9tHURbaqqk-FNUw/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90My5m/dGNkbi5uZXQvanBn/LzAyLzY5LzUxLzEw/LzM2MF9GXzI2OTUx/MTA2OF9tVlhDTGJI/dTJtTDFyZGNUMWFZ/dlR2QU1TOENYZ2JC/Si5qcGc"
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