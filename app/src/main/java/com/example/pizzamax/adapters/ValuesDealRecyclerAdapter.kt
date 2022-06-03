package com.example.pizzamax.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals

class ValuesDealRecyclerAdapter :
    RecyclerView.Adapter<ValuesDealRecyclerAdapter.RecyclerViewHolder>() {

    private var oldData = emptyList<ValuesDeals>()

    //bind the recycler list items
    class RecyclerViewHolder(val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root)

    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.deal.text = oldData[position].id.toString()
        holder.binding.price.text = oldData[position].price
        holder.binding.description.text = oldData[position].size.toString()
        // holder.binding.posterBanner.setImageDrawable()
    }

    override fun getItemCount(): Int {
        return oldData.size
    }


    class ListComparator : DiffUtil.ItemCallback<ValuesDeals>() {
        override fun areItemsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
            return oldItem == newItem
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(newItem: List<ValuesDeals>){
        oldData = newItem
        notifyDataSetChanged()
    }


}