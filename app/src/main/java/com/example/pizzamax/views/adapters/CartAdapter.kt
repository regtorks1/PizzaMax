package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.databinding.CartListBinding
import com.example.pizzamax.model.Cart

class CartAdapter(private val impl: CartImpl) :
    ListAdapter<Cart, CartAdapter.RecyclerViewHolder>(ListComparator()) {
    //bind the recycler list items
    inner class RecyclerViewHolder(val binding: CartListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: Cart?) {
            binding.itemTitle.text = list?.itemName + list?.id.toString()
            binding.price.text = list?.price
            val amount = Integer.parseInt(list?.price.toString())
            val price = Integer.parseInt(binding.price.text.toString())
            binding.amount.text = (amount + price).toString()
            binding.quantity.text = list?.quantity.toString()
            binding.pizzaFlavor.text = ""
//                Resources.getSystem().getString(R.string.choose_your_crust) + list?.crust
            binding.pizzaFlavor.text = ""
            // Resources.getSystem().getString(R.string.pizza_flavors) + list?.flavors
        }
    }

    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            CartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val getItemPosition = getItem(position)
        holder.bind(getItemPosition)

        holder.binding.addBtn.setOnClickListener {

            Log.d("ITEM CLICKED", "::::::::::::::::::YOU CLICK")

        }
        holder.binding.negBtn.setOnClickListener {
            Log.d("ITEM CLICKED", "::::::::::::::::::YOU CLICK")
        }

        holder.itemView.setOnClickListener {
            impl.viewAllCartList(getItemPosition)
        }


    }


    class ListComparator : DiffUtil.ItemCallback<Cart>() {
        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface CartImpl{
        fun viewAllCartList(cart: Cart)
    }

}