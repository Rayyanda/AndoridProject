package com.bootcamp.dhihyarayyanda_mealdb.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.dhihyarayyanda_mealdb.R
import com.bootcamp.dhihyarayyanda_mealdb.databinding.ItemRowMealBinding
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseIdMeal
import com.bootcamp.dhihyarayyanda_mealdb.ui.DetailMealActivity
import com.bumptech.glide.Glide

class MealAdapter():RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    /*private val diffCallBack = object :DiffUtil.ItemCallback<MealsItem>(){
        override fun areItemsTheSame(oldItem : MealsItem, newItem :MealsItem):Boolean{
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealsItem, newItem: MealsItem): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,diffCallBack)
    private lateinit var onItemCallBack:IOnItemCallBack*/

    private var dataMeal: List<MealsItem> = listOf()
    private var allDataMeal:List<ResponseIdMeal> = listOf()
    var id = ""
    inner class MealViewHolder(val binding: ItemRowMealBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data:MealsItem){
                    binding.apply {
                        tvFoodName.text = data.strMeal

                        Glide.with(imgFood)
                            .load(data.strMealThumb)
                            .error(R.drawable.ic_launcher_background)
                            .into(imgFood)

                        id = data.idMeal!!
                        }
                    }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowBinding = ItemRowMealBinding.inflate(layoutInflater,parent,false)
        return MealViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return dataMeal.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val item = dataMeal[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{

            val context = holder.itemView.context
            val intent = Intent(context,DetailMealActivity::class.java)

            println(item.idMeal)
            intent.putExtra(DetailMealActivity.EXTRA_Meal,item)
            context.startActivity(intent)
        }


    }
    fun setData(data: List<MealsItem>){
        dataMeal = data
        notifyDataSetChanged()
    }
    fun setDataMeal(data: List<ResponseIdMeal>){
        allDataMeal = data
        notifyDataSetChanged()
    }
    interface IOnItemCallBack{
        fun onItemClickCallBack(data:MealsItem)
    }
}