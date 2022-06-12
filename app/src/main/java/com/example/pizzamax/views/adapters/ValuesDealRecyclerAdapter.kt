package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
<<<<<<< HEAD
=======
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import android.widget.Toast
>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals

<<<<<<< HEAD
class ValuesDealRecyclerAdapter(
    private val adapterImpl: AdapterListImpl,
    private val itemClick: (title: String, price: String) -> Unit
) :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {
    //bind the recycler list items
    inner class RecyclerViewHolder(
        val binding: RecyclerListBinding
    ) :
=======


class ValuesDealRecyclerAdapter(
    private val updateCheckout: UpdateCheckout,
    private val main: ValueDealsFragment,
    private val showDetails: ShowDetails,
    private val favoriteClickInterface: FavoriteClickInterface
) :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {
     private val alertFragment = MainAlertFragment()
      private val getList = ArrayList<ValuesDeals>()



    //bind the recycler list items
    inner class RecyclerViewHolder(val binding: RecyclerListBinding, var alertdialogBinding: FirstAlertdialogBinding) :

>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: ValuesDeals?) {
            binding.deal.text = "Deal " + list?.id.toString()
            binding.price.text = "Ghc " + list?.price
            binding.pizzaSize.text = list?.size.toString()
            binding.posterBanner.load(list?.imgUrl)
        }
    }

    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val getItemPosition = getItem(position)
        holder.bind(getItemPosition)

        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl)
            .into(holder.binding.posterBanner)

        //holder.binding.deal.text= getList[position].id.toString()
        holder.binding.description.text = getList[position].size
        holder.binding.price.text = getList[position].price

        holder.binding.addCart.setOnClickListener {
<<<<<<< HEAD
            itemClick("Deal ${getItemPosition.id}", getItemPosition.price)
=======

           main.mainAlertDialog("Deal ${getItemPosition.id}",getItemPosition.price)
          //  CustomDialog()

            // updateCheckout.onAddCart(postN)

            main.alertDialog()

        }
        holder.binding.favoriteHeart.setOnClickListener {
            favoriteClickInterface.onFavoriteClick(getList[position])
        }

    }

    override fun getItemCount(): Int {
        return getList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<ValuesDeals>) {
        getList.clear()
        getList.addAll(newList)
        notifyDataSetChanged()
    }

    interface FavoriteClickInterface {
        fun onFavoriteClick(valuesDeals: ValuesDeals)


>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
        }

        holder.itemView.setOnClickListener {
            adapterImpl.onDetailsOnItemClicked(getItemPosition)
        }

        holder.binding.favoriteHeart.setOnClickListener {
            adapterImpl.addToFavorites(getItemPosition)
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

    /*interface ValueDealsAdapterImpl {
        fun onAddCart(cart: ValuesDeals)
        fun addToFavorites(favorites: ValuesDeals)
        fun onDetailsOnItemClicked(details: ValuesDeals)
    }*/


}