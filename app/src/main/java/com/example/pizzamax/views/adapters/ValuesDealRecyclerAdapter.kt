package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals

class ValuesDealRecyclerAdapter :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {

    //bind the recycler list items
  inner class RecyclerViewHolder(private val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ValuesDeals?) {
            binding.deal.text = list?.id.toString()
            binding.price.text = list?.price
            binding.description.text = list?.size.toString()
            binding.posterBanner.load(list?.image)

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
        val postN = getItem(position)
        holder.bind(postN)
    }


     class ListComparator : DiffUtil.ItemCallback<ValuesDeals>() {
        override fun areItemsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
            return oldItem.id == newItem.id
        }
    }

}