package com.bootcamp.dhihyarayyanda_mealdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.dhihyarayyanda_mealdb.R
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity
import com.bootcamp.dhihyarayyanda_mealdb.databinding.FavoritItemMealBinding
import com.bumptech.glide.Glide

class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<MealEntity>() {
        override fun areItemsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    private lateinit var onFavoriteItemCallBack: IOnFavoriteItemCallBack

    inner class FavoriteViewHolder(private val binding: FavoritItemMealBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:MealEntity){

            binding.apply{
                Glide.with(imgFavMeal)
                    .load(item.strMealThumb)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgFavMeal)
                tvFavMeal.text = item.strMeal
                itemView.setOnClickListener{
                    onFavoriteItemCallBack.onFavoriteItemClickCallback(item)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoritItemMealBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val itemData = differ.currentList[position]
        holder.bind(itemData)
    }

    interface IOnFavoriteItemCallBack{
        fun onFavoriteItemClickCallback(data : MealEntity)
    }
    fun setData(list:List<MealEntity>?){
        differ.submitList(list)
    }
    fun setOnItemClickCallBack(action: IOnFavoriteItemCallBack){
        this.onFavoriteItemCallBack = action
    }

}