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
import com.example.pizzamax.databinding.ActivityCheckoutBinding
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals


class ValuesDealRecyclerAdapter(
    context: Context,
    private  val updateCheckout: UpdateCheckout
) :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {

    //bind the recycler list items
  inner class RecyclerViewHolder(val binding: RecyclerListBinding, val cartBinding: ActivityCheckoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: ValuesDeals?) {
            binding.deal.text = "Deal "+ list?.id.toString()
            binding.price.text = "Ghc "+list?.price
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
        val getItemPosition = getItem(position)
        holder.bind(getItemPosition)
        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl).into(holder.binding.posterBanner)
        holder.binding.addCart.setOnClickListener {
            updateCheckout.onAddCart(getItemPosition)
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

    interface UpdateCheckout{
        fun onAddCart(cart: ValuesDeals)
    }


}