package com.bootcamp.dhihyarayyanda_mealdb.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bootcamp.dhihyarayyanda_mealdb.data.LocalDataSource
import com.bootcamp.dhihyarayyanda_mealdb.data.Repository
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealDatabase
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity

class FavoriteViewModel(application: Application):AndroidViewModel(application) {
    //LOCAl
    private val mealDao = MealDatabase.getDatabase(application).mealDao()
    private val local = LocalDataSource(mealDao)

    private val repository = Repository(local = local)

    val favoriteMealList: LiveData<List<MealEntity>> = repository.local!!.listMeal().asLiveData()
}