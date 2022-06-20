package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.Categories
import com.example.pizzamax.model.CategoriesList

class ProductListAdapter(
    private val adapterImpl: AdapterListImpl,
    private val itemClick: (title: String, price: String) -> Unit
) :
    ListAdapter<CategoriesList, ProductListAdapter.RecyclerViewHolder>(ListComparator()) {
    //bind the recycler list items
    inner class RecyclerViewHolder(val binding: RecyclerListBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: CategoriesList?) {
            binding.deal.text = product?.id.toString()
            binding.price.text = product?.price
            binding.pizzaSize.text = product?.size
            binding.posterBanner.load(product?.imgUrl)
        }
    }
    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val getItemPosition = getItem(position)

        holder.bind(getItemPosition)
        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl)
            .into(holder.binding.posterBanner)
    }

    class ListComparator : DiffUtil.ItemCallback<CategoriesList>() {
        override fun areItemsTheSame(oldItem: CategoriesList, newItem: CategoriesList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoriesList, newItem: CategoriesList): Boolean {
            return oldItem.id == newItem.id
        }
    }

}