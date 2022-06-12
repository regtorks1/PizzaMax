package com.example.pizzamax.views.adapters

import android.annotation.SuppressLint
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.ViewGroup
=======
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
<<<<<<< HEAD
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.Favorites

class FavoritesAdapter(
    private val listeners: OnFavoriteDetailPage,
    private val itemClick: (title: String, price: String) -> Unit
) :
    ListAdapter<Favorites, FavoritesAdapter.RecyclerViewHolder>(ListComparator()) {
    //bind the recycler list items
  inner class RecyclerViewHolder(val binding: RecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: Favorites?) {
            binding.deal.text = "Deal "+ list?.id.toString()
            binding.price.text = "Ghc "+list?.price
            binding.pizzaSize.text = list?.size.toString()
            binding.posterBanner.load(list?.imgUrl)
        }
    }

    //inflate the List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //bind the model list the recycler list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val getItemPosition = getItem(position)
        holder.bind(getItemPosition)
        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl).into(holder.binding.posterBanner)
        holder.binding.addCart.setOnClickListener {
            itemClick("Deal ${getItemPosition.id}", getItemPosition.price)
        }

        holder.itemView.setOnClickListener {
            listeners.viewDetail(getItemPosition)
        }

        holder.binding.favoriteHeart.setOnClickListener {
           listeners.onItemRemoveClick(getItemPosition.id)
        }
    }


     class ListComparator : DiffUtil.ItemCallback<Favorites>() {
        override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface OnFavoriteDetailPage{
        fun viewDetail(favorites: Favorites)
        fun onItemRemoveClick(position: Int)
    }

}
=======
import com.example.pizzamax.R
import com.example.pizzamax.databinding.RecyclerListBinding
import com.example.pizzamax.model.ValuesDeals
import com.example.pizzamax.views.ui.fragments.ValueDealsFragment

class FavoritesAdapter(val context: Context,
                       private val favoriteClickInterface: FavoriteClickInterface
): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private val getList = ArrayList<ValuesDeals>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterBanner: ImageView = itemView.findViewById(R.id.poster_banner)
        val deal: TextView = itemView.findViewById(R.id.deal)
        val description: TextView = itemView.findViewById(R.id.description)
        val priceBack: ImageView = itemView.findViewById(R.id.price_back)
        val priceVal: TextView = itemView.findViewById(R.id.price)
        val favoriteHeart: ImageView = itemView.findViewById(R.id.favorite_heart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_list,
            parent,false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
//        val getItemPosition = getItem(position)
//        holder.bind(getItemPosition)
        holder.deal.setText(getList.get(position).id)
        holder.description.setText(getList.get(position).size)
        holder.priceVal.setText(getList.get(position).price)
//        Glide.with(holder.itemView.context).load(getItem(position).imgUrl)
//            .into(holder.posterBanner)

        holder.favoriteHeart.setOnClickListener{
            favoriteClickInterface.onFavoriteClick(getList.get(position))
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


}

interface FavoriteClickInterface {
    fun onFavoriteClick(valuesDeals: ValuesDeals)
}


//class FavoritesAdapter(
//    private val context : Context,
//    private val favoriteClickInterface: FavoriteClickInterface
//) :
//    ListAdapter<ValuesDeals, FavoritesAdapter.RecyclerViewHolder>(ListComparator()) {
//
//    private val getList = ArrayList<ValuesDeals>()
//
//    //bind the recycler list items
//    inner class RecyclerViewHolder(val binding: RecyclerListBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        @SuppressLint("SetTextI18n")
//        fun bind(list: ValuesDeals?) {
//            binding.deal.text = "Deal " + list?.id.toString()
//            binding.price.text = "Ghc " + list?.price
//            binding.description.text = list?.size.toString()
//            binding.posterBanner.load(list?.imgUrl)
//            binding.favoriteHeart
//        }
//    }
//
//    //inflate the List
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
//        return RecyclerViewHolder(
//            RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
//        )
//    }
//
//    //bind the model list the recycler list
//    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
//        val getItemPosition = getItem(position)
//        holder.bind(getItemPosition)
//        Glide.with(holder.itemView.context).load(getItemPosition.imgUrl)
//            .into(holder.binding.posterBanner)
//
//        holder.binding.favoriteHeart.setOnClickListener {
//            favoriteClickInterface.onFavoriteClick(getList.get(position))
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return getList.size
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun updateList(newList: List<ValuesDeals>) {
//        getList.clear()
//        getList.addAll(newList)
//        notifyDataSetChanged()
//    }
//
//    class ListComparator : DiffUtil.ItemCallback<ValuesDeals>() {
//        override fun areItemsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: ValuesDeals, newItem: ValuesDeals): Boolean {
//            return oldItem.id == newItem.id
//        }
//    }
//}
//
//
//    interface FavoriteClickDeleteInterface {
//        fun onDeleteIconClick(valuesDeals: ValuesDeals)
//    }
//
//    interface FavoriteClickInterface {
//        fun onFavoriteClick(valuesDeals: ValuesDeals)
//    }
>>>>>>> ca30c47b45643607646ef7d8be4b2093188026ac
