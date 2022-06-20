package com.example.pizzamax.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.R
import com.example.pizzamax.databinding.CartListBinding
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.CategoriesList

class ProductRecyclerViewAdapter(
    private val adapterImpl: AdapterListImpl,
    private val itemClick: (title: String, price: String) -> Unit
) : RecyclerView.Adapter<RecyclerViewTypeHolder>() {

    var items = listOf<CategoriesList>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewTypeHolder {
        return when (viewType) {
            R.layout.recycler_list -> RecyclerViewTypeHolder.ProductViewTypeHolder(
                RecyclerListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.cart_list -> RecyclerViewTypeHolder.CartViewTypeHolder(
                CartListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewTypeHolder, position: Int) {
        when (holder) {
            is RecyclerViewTypeHolder.ProductViewTypeHolder -> holder.bind(items[position])
            is RecyclerViewTypeHolder.CartViewTypeHolder -> TODO()
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
}

/*override fun getItemViewType(position: Int): Int {
    return when (position) {
        is  -> {
            R.layout.recycler_list
        }

        is Cart -> {
            R.layout.cart_list
        }
        else -> {

        }
    }
}*/


//override fun getItemCount() = items.size