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
import com.epn.projectconectatour.network.RetrofitClient
import com.epn.projectconectatour.network.models.AtractivoHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SitesAdapter
    private val atractivos = mutableListOf<AtractivoHome>()

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

        adapter = SitesAdapter(atractivos)
        recyclerView.adapter = adapter

        cargarAtractivos()
    }

    // ===============================
    // LLAMADA A LA API
    // ===============================
    private fun cargarAtractivos() {
        RetrofitClient.atractivosApi.getAtractivosHome()
            .enqueue(object : Callback<List<AtractivoHome>> {

                override fun onResponse(
                    call: Call<List<AtractivoHome>>,
                    response: Response<List<AtractivoHome>>
                ) {
                    if (response.isSuccessful) {
                        atractivos.clear()
                        atractivos.addAll(response.body() ?: emptyList())
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<AtractivoHome>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    // ===============================
    // ADAPTER
    // ===============================
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