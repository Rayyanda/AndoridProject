package com.bootcamp.dhihyarayyanda_mealdb.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bootcamp.dhihyarayyanda_mealdb.R
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity
import com.bootcamp.dhihyarayyanda_mealdb.databinding.ActivityFavoriteBinding
import com.bootcamp.dhihyarayyanda_mealdb.databinding.ActivityFavoriteDetailBinding
import com.bootcamp.dhihyarayyanda_mealdb.viewModel.FavoriteDetailViewModel
import com.bumptech.glide.Glide

class FavoriteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteDetailBinding
    private val favoriteDetailViewModel by viewModels<FavoriteDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Favorite Food Detail"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#84878e")))
            setDisplayHomeAsUpEnabled(true)
        }
        val favoriteMeal = intent.getParcelableExtra<MealEntity>(EXTRA_FAVORITE_MEAL)
        binding.apply {
            tvMealname.text = favoriteMeal?.strMeal
            Glide.with(imgMeal)
                .load(favoriteMeal?.strMealThumb)
                .error(R.drawable.ic_launcher_background)
                .into(imgMeal)
            tvMealArea.text = favoriteMeal?.strArea
            tvMealInstruction.text = favoriteMeal?.strInstruction
            binding.delFromFavorite.setOnClickListener{
                deleteFromFavorite(favoriteMeal!!)
                val intent = Intent(this@FavoriteDetailActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }
    private fun deleteFromFavorite(mealEntity: MealEntity){
    favoriteDetailViewModel.deleteFavoriteMeal(mealEntity)
    Toast.makeText(this, "Successfully remove from favorite", Toast.LENGTH_SHORT).show()
    }
    companion object{
        const val EXTRA_FAVORITE_MEAL = "favorite_meal"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}