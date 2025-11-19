package com.epn.projectconectatour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.sitesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Datos de prueba
        val sites = listOf(
            Site("Monumento", "Monumento Virgen del Panecillo", "https://vistahermosa.ec/wp-content/uploads/2018/07/panecillo-vista-hermosa.jpg"),
            Site("Histórico", "Mitad del Mundo", "https://imgs.search.brave.com/-Jz5QNxickBGTlglCVpNAMH3x9sZ9tHURbaqqk-FNUw/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90My5m/dGNkbi5uZXQvanBn/LzAyLzY5LzUxLzEw/LzM2MF9GXzI2OTUx/MTA2OF9tVlhDTGJI/dTJtTDFyZGNUMWFZ/dlR2QU1TOENYZ2JC/Si5qcGc"),
            Site("Mirador", "Cruz Loma (Teleférico)", "https://imgs.search.brave.com/719byVKkQKjCEitLOxduYee96SI5YpU6EcGxas4um-c/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vd3d3LnRo/ZWdpcmx3aG9nb2Vz/LmNvbS93cC1jb250/ZW50L3VwbG9hZHMv/MjAyMi8wMi90ZWxl/ZmVyaWNvLWhlYWRl/ci1lMTY0NDQ1ODE2/Mjg1OS0xMDI0eDY4/Ny5qcGc_cmVzaXpl/PTc3Miw1MTgmc3Ns/PTE"),
            Site("Iglesia", "La Basílica del Voto Nacional","https://imgs.search.brave.com/rmjs5veGgi4uWeBIs5whKmu4GJWG9tBys75yg10zkZ0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9sb3N0/cmF2ZWxlcm9zLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAy/My8wMi9Db25kb3It/VmlzdGEtODAweDUz/Mi5qcGc")
        )

        // Asignar el adaptador
        recyclerView.adapter = SitesAdapter(sites)
    }

    // --- CLASES PARA LA LISTA (ADAPTER Y MODELO) ---

    // Modelo de datos simple
    data class Site(val category: String, val title: String, val imageUrl: String)

    // Adaptador para el RecyclerView
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
            Glide.with(holder.itemView.context).load(site.imageUrl).into(holder.imageView)
        }

        override fun getItemCount() = siteList.size
    }
}