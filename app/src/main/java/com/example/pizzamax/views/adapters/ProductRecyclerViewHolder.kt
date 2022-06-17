package com.example.pizzamax.views.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.pizzamax.databinding.CartListBinding
import com.example.pizzamax.databinding.RecyclerListBinding

sealed class ProductRecyclerViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

     class ProductViewType( val binding: RecyclerListBinding) : ProductRecyclerViewHolder(binding) {
         fun bind(deals: ProductRecyclerViewItem.ValuesDeals) {
             binding.deal.text = "Deal " + deals.id.toString()
             binding.price.text = "Ghc " + deals.price
             binding.pizzaSize.text = deals.size.toString()
             binding.posterBanner.load(deals.imgUrl)
         }

         fun bind(better: ProductRecyclerViewItem.BigBetter) {
             binding.deal.text = "Big Better " + better.id.toString()
             binding.price.text = "Ghc " + better.price
             binding.pizzaSize.text = better.size.toString()
             binding.posterBanner.load(better.imgUrl)
         }

         fun bind(appetizers: ProductRecyclerViewItem.Appetizers) {
             binding.deal.text = "Appetizers " + appetizers.id.toString()
             binding.price.text = "Ghc " + appetizers.price
             binding.pizzaSize.text = appetizers.size.toString()
             binding.posterBanner.load(appetizers.imgUrl)
         }

         fun bind(signature: ProductRecyclerViewItem.SignaturePizza) {
             binding.deal.text = "Signature " + signature.id.toString()
             binding.price.text = "Ghc " + signature.price
             binding.pizzaSize.text = signature.size.toString()
             binding.posterBanner.load(signature.imgUrl)
         }

         fun bind(expenses: ProductRecyclerViewItem.Expenses) {
             binding.deal.text = expenses.id.toString()
         }

         fun bind(favorites: ProductRecyclerViewItem.Favorites) {
              binding.deal.text = "Deal "+ favorites.id.toString()
             binding.price.text = "Ghc "+favorites.price
             binding.pizzaSize.text = favorites.size.toString()
             binding.posterBanner.load(favorites.imgUrl)
         }
     }

   /* class ValuesDealsViewType(val binding: RecyclerListBinding) :
        ProductRecyclerViewHolder(binding) {
        fun bind(deals: ProductRecyclerViewItem.ValuesDeals) {
            binding.deal.text = "Deal " + deals.id.toString()
            binding.price.text = "Ghc " + deals.price
            binding.pizzaSize.text = deals.size.toString()
            binding.posterBanner.load(deals.imgUrl)
        }
    }

    class BetterViewType(val binding: RecyclerListBinding) :
        ProductRecyclerViewHolder(binding) {
        fun bind(better: ProductRecyclerViewItem.BigBetter) {
            binding.deal.text = "Big Better " + better.id.toString()
            binding.price.text = "Ghc " + better.price
            binding.pizzaSize.text = better.size.toString()
            binding.posterBanner.load(better.imgUrl)
        }
    }

    class AppetizersViewType(val binding: RecyclerListBinding) :
        ProductRecyclerViewHolder(binding) {
        fun bind(appetizers: ProductRecyclerViewItem.Appetizers) {
            binding.deal.text = "Appetizers " + appetizers.id.toString()
            binding.price.text = "Ghc " + appetizers.price
            binding.pizzaSize.text = appetizers.size.toString()
            binding.posterBanner.load(appetizers.imgUrl)
        }
    }

    class SignatureViewType(val binding: RecyclerListBinding) :
        ProductRecyclerViewHolder(binding) {
        fun bind(signature: ProductRecyclerViewItem.SignaturePizza) {
            binding.deal.text = "Signature " + signature.id.toString()
            binding.price.text = "Ghc " + signature.price
            binding.pizzaSize.text = signature.size.toString()
            binding.posterBanner.load(signature.imgUrl)
        }

    }


    class FavoriteViewType(val binding: RecyclerListBinding) :
        ProductRecyclerViewHolder(binding) {
        fun bind(favorites: ProductRecyclerViewItem.Favorites) {
            binding.deal.text = "Deal " + favorites.id.toString()
            binding.price.text = "Ghc " + favorites.price
            binding.pizzaSize.text = favorites.size.toString()
            binding.posterBanner.load(favorites.imgUrl)
        }
    }*/

    class CartViewType(val binding: CartListBinding) : ProductRecyclerViewHolder(binding) {
        fun bind(cart: ProductRecyclerViewItem.Cart) {
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
