package com.bootcamp.dhihyarayyanda_mealdb.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.bootcamp.dhihyarayyanda_mealdb.data.LocalDataSource
import com.bootcamp.dhihyarayyanda_mealdb.data.RemoteDataSource
import com.bootcamp.dhihyarayyanda_mealdb.data.Repository
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealDatabase
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity
import com.bootcamp.dhihyarayyanda_mealdb.data.network.Service
import com.bootcamp.dhihyarayyanda_mealdb.data.network.handler.NetworkResult
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseIdMeal
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel (application: Application): AndroidViewModel(application) {

    //Api
    private val mealService = Service.MealId
    private val remote = RemoteDataSource(mealService)

    //LOCAL
    private val mealDao = MealDatabase.getDatabase(application).mealDao()
    private val local = LocalDataSource(mealDao)

    private val repository = Repository(remote,local)

    private var _idMeal: MutableLiveData<NetworkResult<ResponseIdMeal>> = MutableLiveData()
    val idMeal: LiveData<NetworkResult<ResponseIdMeal>> = _idMeal



    fun fetchIdMeal(idMeal : String){
        viewModelScope.launch {
            repository.remote!!.getIdMeal(idMeal).collect{ res->
                _idMeal.value = res
            }
        }
    }

   val favoriteMealList:LiveData<List<MealEntity>> = repository.local!!.listMeal().asLiveData()

    fun insertFavoriteMeal(mealEntity: MealEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.local!!.insertMeal(mealEntity)
        }
    }
    fun deleteFavoriteMeal(mealEntity: MealEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.local!!.deleteMeal(mealEntity)
        }
    }
}