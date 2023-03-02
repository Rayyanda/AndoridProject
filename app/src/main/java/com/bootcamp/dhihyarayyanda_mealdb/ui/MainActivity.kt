package com.bootcamp.dhihyarayyanda_mealdb.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.dhihyarayyanda_mealdb.R
import com.bootcamp.dhihyarayyanda_mealdb.adapter.MealAdapter

import com.bootcamp.dhihyarayyanda_mealdb.data.network.handler.NetworkResult
import com.bootcamp.dhihyarayyanda_mealdb.databinding.ActivityMainBinding
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem
import com.bootcamp.dhihyarayyanda_mealdb.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val mealAdapter by lazy {
        MealAdapter()
    }
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Meal List"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#84878e")))
        }

        binding.btnToFavorit.setOnClickListener{
            val intent = Intent(this@MainActivity,FavoriteActivity::class.java)
            startActivity(intent)
        }

        mainViewModel.mealList.observe(this@MainActivity){res->
            when(res){
                is NetworkResult.Loading ->{
                    handleUi(
                        recyclerView = false,
                        progressbar = true,
                        errorTv = false
                    )
                }
                is NetworkResult.Error ->{
                    binding.errorText.text = res.errorMessage
                    handleUi(
                        recyclerView = false,
                        progressbar = false,
                        errorTv = true
                    )
                }
                is NetworkResult.Success -> {
                    val mealAdapter = MealAdapter()
                    Log.d("Success", "Success retrieved data")
                    mealAdapter.setData(res.data?.meals as List<MealsItem>)

                    binding.rvMeal.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = mealAdapter
                    }
                    handleUi(
                        recyclerView = true,
                        progressbar = false,
                        errorTv = false
                    )
                }
            }
        }



    }

    private fun handleUi(
        recyclerView: Boolean,
        progressbar: Boolean,
        errorTv:Boolean) {
        binding.apply {
            rvMeal.isVisible = recyclerView
            progressBar.isVisible = progressbar
            errorText.isVisible = errorTv
        }
    }
}