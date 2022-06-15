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
import com.example.pizzamax.model.Favorites

class FavoritesAdapter(
    private val listeners: OnFavoriteDetailPage,
    private val itemClick: (title: String, price: String) -> Unit
) :
    ListAdapter<Favorites, FavoritesAdapter.RecyclerViewHolder>(ListComparator()) {
    private  val allList = ArrayList<Favorites>()
    //bind the recycler list items
  inner class RecyclerViewHolder(val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: Favorites?) {
            binding.deal.text = "Deal "+ list?.id.toString()
            binding.price.text = "Ghc "+list?.price
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
        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl).into(holder.binding.posterBanner)
        holder.binding.addCart.setOnClickListener {
            itemClick("Deal ${getItemPosition.id}", getItemPosition.price)
        }

        holder.itemView.setOnClickListener {
            listeners.viewDetail(getItemPosition)
        }

        holder.binding.favoriteHeart.setOnClickListener {
          // listeners.onItemRemoveClick(allList[position])
            listeners.onDeleteAllClick(getItemPosition.id)
        }
    }


     class ListComparator : DiffUtil.ItemCallback<Favorites>() {
        override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface OnFavoriteDetailPage{
        fun viewDetail(favorites: Favorites)
        fun onItemRemoveClick(item: Favorites)
        fun onDeleteAllClick(position: Int)
    }

}
