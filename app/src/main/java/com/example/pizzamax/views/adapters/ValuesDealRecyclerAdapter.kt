package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pizzamax.databinding.ActivityCheckoutBinding
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.views.CheckoutActivity


class ValuesDealRecyclerAdapter :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {

    //bind the recycler list items
  inner class RecyclerViewHolder(val binding: RecyclerListBinding, val cartBinding: ActivityCheckoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ValuesDeals?) {
            binding.deal.text = list?.id.toString()
            binding.price.text = list?.price
            binding.description.text = list?.size.toString()
            binding.posterBanner.load(list?.imgUrl)

        }
    }

    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
             ActivityCheckoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),

        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val postN = getItem(position)
        holder.bind(postN)
        Glide.with(holder.itemView.context).load(postN.imgUrl).into(holder.binding.posterBanner)
        holder.binding.addCart.setOnClickListener {
            val intent = Intent(holder.itemView.context, CheckoutActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
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