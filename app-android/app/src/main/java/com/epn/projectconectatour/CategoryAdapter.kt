package com.epn.projectconectatour

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epn.projectconectatour.R
import com.epn.projectconectatour.network.models.AtractivoHome
import com.epn.projectconectatour.network.models.CategoriaHome
import com.epn.projectconectatour.SiteDetailActivity

class CategoryAdapter(
    private val categorias: List<CategoriaHome>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryTitle: TextView = view.findViewById(R.id.categoryTitleTextView)
        val verTodo: TextView = view.findViewById(R.id.verTodoTextView)
        val recyclerHorizontal: RecyclerView =
            view.findViewById(R.id.horizontalRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_home, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoria = categorias[position]
        // Título de la categoría
        holder.categoryTitle.text = categoria.categoria

        // Recycler horizontal
        holder.recyclerHorizontal.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = SitesHorizontalAdapter(categoria.atractivos)
            setHasFixedSize(true)
        }

        // Botón "Ver todo"
        holder.verTodo.setOnClickListener {
            val intent = Intent(holder.itemView.context, CategoryDetailActivity::class.java)
            intent.putExtra("categoria", categoria.categoria)
            holder.itemView.context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int = categorias.size
}
