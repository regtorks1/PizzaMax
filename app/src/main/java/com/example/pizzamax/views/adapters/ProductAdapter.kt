package com.example.pizzamax.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.CategoriesList

class ProductAdapter(private val listA: ArrayList<CategoriesList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemViewHolder(var viewBinding: RecyclerListBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.viewBinding.deal.text = listA[position].id.toString()
        itemViewHolder.viewBinding.price.text = listA[position].price
        itemViewHolder.viewBinding.pizzaSize.text = listA[position].size.toString()
    }

    override fun getItemCount(): Int {
        return listA.size
    }
}