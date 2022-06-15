package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.BigBetter

class BigBetterAdapter (
     private val adapterImpl: AdapterListImpl,
     private val itemClick: (title: String, price: String) -> Unit
) :
    ListAdapter<BigBetter, BigBetterAdapter.RecyclerViewHolder>(ListComparator()) {

    //bind the recycler list items
    inner class RecyclerViewHolder(val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: BigBetter?) {
            binding.deal.text = "Big Better " + list?.id.toString()
            binding.price.text = "Ghc " + list?.price
            binding.pizzaSize.text = list?.size.toString()
            binding.posterBanner.load(list?.imgUrl)
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

        holder.binding.addCart.setOnClickListener {
            getItemPosition.price?.let { it1 -> itemClick("Deal ${getItemPosition.id}", it1) }
        }

        holder.itemView.setOnClickListener {
            adapterImpl.onDetailsOnItemClicked(getItemPosition)
        }

        holder.binding.favoriteHeart.setOnClickListener {
            adapterImpl.addToFavorites(getItemPosition)
        }

    }


    class ListComparator : DiffUtil.ItemCallback<BigBetter>() {
        override fun areItemsTheSame(oldItem: BigBetter, newItem: BigBetter): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BigBetter, newItem: BigBetter): Boolean {
            return oldItem.id == newItem.id
        }
    }

}


