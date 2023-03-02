package com.bootcamp.dhihyarayyanda_mealdb.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.dhihyarayyanda_mealdb.R
import com.bootcamp.dhihyarayyanda_mealdb.adapter.FavoriteAdapter
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity
import com.bootcamp.dhihyarayyanda_mealdb.databinding.ActivityFavoriteBinding
import com.bootcamp.dhihyarayyanda_mealdb.viewModel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding :ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val favoriteMealAdapter by lazy { FavoriteAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Favorite Food"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#84878e")))
            setDisplayHomeAsUpEnabled(true)
        }
        favoriteViewModel.favoriteMealList.observe(this){res ->
            favoriteMealAdapter.apply {
                setData(res)
                setOnItemClickCallBack(object : FavoriteAdapter.IOnFavoriteItemCallBack {
                    override fun onFavoriteItemClickCallback(data: MealEntity) {
                        val intent = Intent(this@FavoriteActivity,FavoriteDetailActivity::class.java)
                        intent.putExtra(FavoriteDetailActivity.EXTRA_FAVORITE_MEAL,data)
                        startActivity(intent)
                    }
                })
            }
            if (res.isEmpty()){
                binding.apply {
                    rvFavMeal.isVisible = false
                    errorText.isVisible = true
                }
            }else{
                binding.rvFavMeal.apply {
                    layoutManager = LinearLayoutManager(this@FavoriteActivity)
                    setHasFixedSize(true)
                    adapter = favoriteMealAdapter
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}