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
import com.example.pizzamax.model.Appetizers
import com.example.pizzamax.model.Category
import com.example.pizzamax.model.CategoryItems

class CategoryAdapter(
    private val adapterImpl: AdapterListImpl,
    private val itemClick: (title: String, price: String) -> Unit
) :
ListAdapter<Category,CategoryAdapter.RecyclerViewHolder>(ListComparator()){

    inner class RecyclerViewHolder(val binding: RecyclerListBinding) :
            RecyclerView.ViewHolder(binding.root){
             @SuppressLint("SetTextI18n")
             fun bind(list : Category, list1: CategoryItems){
                 val name = list.categoryName
                 binding.deal.text =  name + list.id.toString()
                 binding.deal.text = list1.id.toString()
                 binding.price.text = list1.price
                 binding.pizzaSize.text = list1.size
                 binding.posterBanner.load(list1.imgUrl)
             }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val getItemPosition: Category = getItem(position)
        holder.bind(getItemPosition)
        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl).into(holder.binding.posterBanner)
        holder.binding.addCart.setOnClickListener {
            itemClick("Deal ${getItemPosition.id}", getItemPosition.price)
        }

        holder.itemView.setOnClickListener {
            adapterImpl.onDetailsOnItemClicked(getItemPosition)
        }

        holder.binding.favoriteHeart.setOnClickListener {
            adapterImpl.addToFavorites(getItemPosition)
        }
    }

    class ListComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }


}