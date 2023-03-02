package com.bootcamp.dhihyarayyanda_mealdb.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.os.Bundle
import android.util.Log
import android.widget.ImageView


import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.BindingMethod
import androidx.lifecycle.ViewModelProvider
import com.bootcamp.dhihyarayyanda_mealdb.R
import com.bootcamp.dhihyarayyanda_mealdb.adapter.FavoriteAdapter
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity


import com.bootcamp.dhihyarayyanda_mealdb.data.network.handler.NetworkResult
import com.bootcamp.dhihyarayyanda_mealdb.databinding.ActivityDetailMealBinding
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem

import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2

import com.bootcamp.dhihyarayyanda_mealdb.viewModel.DetailViewModel
import com.bootcamp.dhihyarayyanda_mealdb.viewModel.MainViewModel
import com.bumptech.glide.Glide



class DetailMealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMealBinding
    lateinit var detailViewModel : DetailViewModel
    private lateinit var allDataMeal : MealsItem2
    private lateinit var mealEntity: MealEntity


    private lateinit var meal : MealsItem
    companion object{

        const val EXTRA_Meal = "meals"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Detail Meal"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#84878e")))
            setDisplayHomeAsUpEnabled(true)
        }

        //detailViewModel = ViewModelProvider(this@DetailMealActivity).get(DetailViewModel::class.java)
        detailViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[DetailViewModel::class.java]

        val dataMeal = intent.getParcelableExtra<MealsItem>(EXTRA_Meal)

        println("ini nilai di detailMeal ${dataMeal?.idMeal}",)

        detailViewModel.fetchIdMeal(dataMeal?.idMeal!!)

        detailViewModel.idMeal.observe(this){res ->
            when(res){
                is NetworkResult.Loading -> handleUi(
                    progressbar = true,
                    layoutWrapper = false,
                    errorTv = false
                )
                is NetworkResult.Error -> {
                    handleUi(
                        progressbar = false,
                        layoutWrapper = false,
                        errorTv = true
                    )
                    Toast.makeText(this, res.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Success -> {
                    //val mealAdapter = MealAdapter()
                    Log.d("Success", "Success retrieved data")
                    println(res.data)
                    binding.detailMeal = res.data?.meals2!![0]
                    allDataMeal = res.data.meals2[0]!!
                    //mealEntity = MealEntity(allDataMeal.idMeal?.toInt()!!,allDataMeal.strMeal!!,allDataMeal.strMealThumb!!,allDataMeal.strArea!!,allDataMeal.strInstructions!!)
                    println(allDataMeal)
                    binding.apply {
                        tvMealname.text = allDataMeal.strMeal
                        tvMealInstruction.text = allDataMeal.strInstructions
                        Glide.with(imgMeal)
                            .load(allDataMeal.strMealThumb)
                            .error(R.drawable.ic_launcher_background)
                            .into(imgMeal)
                    }

                    handleUi(
                        layoutWrapper = true,
                        progressbar = false,
                        errorTv = false
                    )
                    }
                }
            }


        isFavoriteMeal(dataMeal)
        }

    private fun isFavoriteMeal(mealSelected : MealsItem){
        detailViewModel.favoriteMealList.observe(this){res ->
            val meal = res.find { favorite ->
                favorite.id == mealSelected.idMeal?.toInt()
            }
            if (meal != null){
                binding.btnAddToFavorite.apply {
                    //jika makanan adalah makanan favorite
                    setText(R.string.remove_from_favorite)
                    setBackgroundColor(ContextCompat.getColor(this@DetailMealActivity,R.color.red_star))
                    setOnClickListener{
                        deleteFavoriteMeal(mealSelected.idMeal!!.toInt())
                    }
                }
            }else   {
                binding.btnAddToFavorite.apply {
                    setText(R.string.add_to_favorite)
                    setBackgroundColor(
                        ContextCompat.getColor(
                            this@DetailMealActivity,
                            R.color.light_blue
                        )
                    )
                    setOnClickListener {
                        insertFavoriteMeal()
                    }
                }
            }
        }
    }

    private fun deleteFavoriteMeal(mealEntityId : Int){
        val mealEntity = MealEntity(mealEntityId,allDataMeal.strMeal!!,allDataMeal.strMealThumb!!,allDataMeal.strArea!!,allDataMeal.strInstructions!!)
        detailViewModel.deleteFavoriteMeal(mealEntity)
        Toast.makeText(this, "Successfully remove from favorite", Toast.LENGTH_SHORT).show()
    }

    private fun insertFavoriteMeal(){
        val mealEntity = MealEntity(
        strMeal = allDataMeal.strMeal!!,
        strArea = allDataMeal.strArea!!,
        strInstruction = allDataMeal.strInstructions!!,
        strMealThumb = allDataMeal.strMealThumb!!)
        detailViewModel.insertFavoriteMeal(mealEntity)
        Toast.makeText(this, "Successfully added to favorite", Toast.LENGTH_SHORT).show()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun handleUi(
        layoutWrapper:Boolean,
        progressbar:Boolean,
        errorTv:Boolean,
    ){
        binding.apply {
            mealDetail.isVisible = layoutWrapper
            progressBar.isVisible = progressbar
            errorText.isVisible = errorTv
        }
    }
}





