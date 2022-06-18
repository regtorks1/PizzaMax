package com.example.pizzamax.views.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.pizzamax.databinding.CartListBinding
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.Cart
import com.example.pizzamax.model.CategoriesList

sealed class RecyclerViewTypeHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

     class ProductViewTypeHolder(val binding: RecyclerListBinding) : RecyclerViewTypeHolder(binding) {
         fun bind(product: CategoriesList){
             binding.deal.text = product.id.toString()
             binding.price.text = product.price
             binding.pizzaSize.text = product.size
             binding.posterBanner.load(product.imgUrl)
         }
     }

    class CartViewTypeHolder(val binding: CartListBinding) : RecyclerViewTypeHolder(binding) {
        fun bind(cart: Cart) {
            binding.itemTitle.text = cart.itemName + cart.id.toString()
            binding.price.text = cart.price
            val amount = Integer.parseInt(cart.price.toString())
            val price = Integer.parseInt(binding.price.text.toString())
            binding.amount.text = (amount + price).toString()
            binding.quantity.text = cart.quantity.toString()
            binding.pizzaFlavor.text = ""
//                Resources.getSystem().getString(R.string.choose_your_crust) + list?.crust
            binding.pizzaFlavor.text = ""
        }
    }
}
