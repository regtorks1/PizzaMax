package com.example.pizzamax.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.R
import com.example.pizzamax.databinding.CartListBinding
import com.example.pizzamax.databinding.RecyclerListBinding

class ProductRecyclerViewAdapter(
    private val adapterImpl: AdapterListImpl,
    private val itemClick: (title: String, price: String) -> Unit
) : RecyclerView.Adapter<ProductRecyclerViewHolder>() {

    var items = listOf<ProductRecyclerViewItem>()
        set(value) { field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductRecyclerViewHolder {
        return when (viewType) {
            R.layout.recycler_list -> ProductRecyclerViewHolder.ProductViewType(
                    RecyclerListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
/*
                ProductRecyclerViewHolder.BetterViewType(
                    RecyclerListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

                ProductRecyclerViewHolder.AppetizersViewType(
                    RecyclerListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

                ProductRecyclerViewHolder.SignatureViewType(
                    RecyclerListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

                ProductRecyclerViewHolder.FavoriteViewType(
                    RecyclerListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )*/
            R.layout.cart_list -> ProductRecyclerViewHolder.CartViewType(
                CartListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }
    override fun onBindViewHolder(holder: ProductRecyclerViewHolder, position: Int) {
        when (holder) {
            is ProductRecyclerViewHolder.CartViewType -> holder.bind(items[position] as ProductRecyclerViewItem.Cart)
             is ProductRecyclerViewHolder.ProductViewType -> {
                 holder.bind(items[position] as ProductRecyclerViewItem.ValuesDeals)
                 holder.bind(items[position] as ProductRecyclerViewItem.BigBetter)
                 holder.bind(items[position] as ProductRecyclerViewItem.Appetizers)
                 holder.bind(items[position] as ProductRecyclerViewItem.SignaturePizza)
                 holder.bind(items[position] as ProductRecyclerViewItem.Expenses)
                 holder.bind(items[position] as ProductRecyclerViewItem.Favorites)
             }
          /*  is ProductRecyclerViewHolder.AppetizersViewType -> holder.bind(items[position] as ProductRecyclerViewItem.Appetizers)
            is ProductRecyclerViewHolder.BetterViewType -> holder.bind(items[position] as ProductRecyclerViewItem.BigBetter)
            is ProductRecyclerViewHolder.FavoriteViewType -> holder.bind(items[position] as ProductRecyclerViewItem.Favorites)
            is ProductRecyclerViewHolder.SignatureViewType -> holder.bind(items[position] as ProductRecyclerViewItem.SignaturePizza)
            is ProductRecyclerViewHolder.ValuesDealsViewType -> holder.bind(items[position] as ProductRecyclerViewItem.ValuesDeals)*/
        }
    }

    override fun getItemViewType(position: Int): Int {
       return when (items[position]) {
            is ProductRecyclerViewItem.ValuesDeals -> R.layout.recycler_list
            is ProductRecyclerViewItem.BigBetter -> R.layout.recycler_list
            is ProductRecyclerViewItem.Appetizers -> R.layout.recycler_list
            is ProductRecyclerViewItem.SignaturePizza -> R.layout.recycler_list
            is ProductRecyclerViewItem.Cart -> R.layout.cart_list
            is ProductRecyclerViewItem.Expenses -> R.layout.recycler_list
            is ProductRecyclerViewItem.Favorites -> R.layout.recycler_list
        }
    }


    override fun getItemCount() = items.size
}