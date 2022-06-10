package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
import android.content.Context
<<<<<<< HEAD
=======
import android.content.Intent
>>>>>>> f29d83f800298ce357377459f542a272c20ea088
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.pizzamax.databinding.FirstAlertdialogBinding
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment
import com.example.pizzamax.views.util.mainAlertDialog


class ValuesDealRecyclerAdapter(
<<<<<<< HEAD
    private  val updateCheckout: UpdateCheckout,
    private val main : ValueDealsFragment,
    private val favoriteClickInterface: FavoriteClickInterface
) :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {

    private val getList = ArrayList<ValuesDeals>()
=======
    context: Context,
    private val updateCheckout: UpdateCheckout,
    private val main: ValueDealsFragment
) :
    ListAdapter<ValuesDeals, ValuesDealRecyclerAdapter.RecyclerViewHolder>(ListComparator()) {

     private val ctx: Context = context
>>>>>>> f29d83f800298ce357377459f542a272c20ea088

    //bind the recycler list items
    inner class RecyclerViewHolder(val binding: RecyclerListBinding, var alertdialogBinding: FirstAlertdialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: ValuesDeals?) {
            binding.deal.text = "Deal " + list?.id.toString()
            binding.price.text = "Ghc " + list?.price
            binding.description.text = list?.size.toString()
            binding.posterBanner.load(list?.imgUrl)

            alertdialogBinding.priceAlert.text = list?.price
        }
    }

    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            FirstAlertdialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val getItemPosition = getItem(position)
        holder.bind(getItemPosition)

        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl)
            .into(holder.binding.posterBanner)

        //holder.binding.deal.text= getList[position].id.toString()
        holder.binding.description.text = getList[position].size
        holder.binding.price.text = getList[position].price

        holder.binding.addCart.setOnClickListener {
            // updateCheckout.onAddCart(postN)
<<<<<<< HEAD
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
=======

            holder.alertdialogBinding.priceAlert.setText(getItemPosition.price)

            val intent1 = Intent()
            val intent = Intent(ctx, FirstAlertdialogBinding::class.java)
            intent.putExtra("title",getItemPosition.price)
            intent.putExtra("price",getItemPosition.price)
            Log.d("DIALOG PRICE:::::::::", "${intent.getStringExtra("price")}")

            main.mainAlertDialog()
        }
>>>>>>> f29d83f800298ce357377459f542a272c20ea088
    }


    class ListComparator : DiffUtil.ItemCallback<ValuesDeals>() {
        override fun areItemsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface UpdateCheckout {
        fun onAddCart(cart: ValuesDeals)
    }


}