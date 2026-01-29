package com.epn.projectconectatour

import android.content.Intent
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

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.sitesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Usamos tu clase Site externa
        val sites = listOf(
            Site(
                title = "La Basílica del Voto Nacional",
                description = "La Basílica del Voto Nacional es la obra más importante de la arquitectura neogótica ecuatoriana.",
                imageUrl = "https://imgs.search.brave.com/rmjs5veGgi4uWeBIs5whKmu4GJWG9tBys75yg10zkZ0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9sb3N0/cmF2ZWxlcm9zLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAy/My8wMi9Db25kb3It/VmlzdGEtODAweDUz/Mi5qcGc",
                category = "Iglesia"
            ),
            Site(
                title = "Iglesia de San Francisco",
                description = "La Iglesia de San Francisco es una basílica católica que se levanta en medio del centro histórico de Quito.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Iglesia_de_San_Francisco%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_150.JPG/800px-Iglesia_de_San_Francisco%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_150.JPG",
                category = "Iglesia"
            ),
            Site(
                title = "Centro Histórico",
                description = "El Centro Histórico de Quito es el mejor conservado y uno de los más importantes de América Latina.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Plaza_de_la_Independencia%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_129.JPG/800px-Plaza_de_la_Independencia%2C_Quito%2C_Ecuador%2C_2015-07-22%2C_DD_129.JPG",
                category = "Histórico"
            )
        )

        recyclerView.adapter = SitesAdapter(sites)
    }

    inner class SitesAdapter(private val siteList: List<Site>) :
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
            holder.titleTextView.text = site.title
            holder.categoryTextView.text = site.category

            Glide.with(holder.itemView.context)
                .load(site.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView)

            holder.itemView.setOnClickListener {
                // CLAVE DEL ÉXITO: Enviar las partes por separado
                val intent = Intent(holder.itemView.context, SiteDetailActivity::class.java).apply {
                    putExtra("name", site.title)
                    putExtra("description", site.description)
                    putExtra("imageUrl", site.imageUrl)
                }
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount() = siteList.size
    }
}