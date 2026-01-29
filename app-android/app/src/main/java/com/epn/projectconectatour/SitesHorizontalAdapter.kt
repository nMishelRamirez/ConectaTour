package com.epn.projectconectatour

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epn.projectconectatour.R
import com.epn.projectconectatour.network.models.AtractivoHome
import com.epn.projectconectatour.SiteDetailActivity

class SitesHorizontalAdapter(
    private val sitios: List<AtractivoHome>
) : RecyclerView.Adapter<SitesHorizontalAdapter.SiteViewHolder>() {

    inner class SiteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.siteImageView)
        val title: TextView = view.findViewById(R.id.siteTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_site_horizontal, parent, false)
        return SiteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val site = sitios[position]

        holder.title.text = site.nombre

        Glide.with(holder.itemView.context)
            .load(site.imagenPrincipal)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SiteDetailActivity::class.java)
            intent.putExtra("id", site.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = sitios.size
}
