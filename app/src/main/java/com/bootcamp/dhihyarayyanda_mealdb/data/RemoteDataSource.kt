package com.bootcamp.dhihyarayyanda_mealdb.data

import android.util.Log
import com.bootcamp.dhihyarayyanda_mealdb.data.network.api.MealApi
import com.bootcamp.dhihyarayyanda_mealdb.data.network.handler.NetworkResult
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseIdMeal
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit

class RemoteDataSource (private val mealApi: MealApi){



    suspend fun getMeal():Flow<NetworkResult<ResponseMeal>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val meal = mealApi.getMeal("seafood")

            //request data successfully
            if (meal.isSuccessful){
                val responseBody = meal.body()
                val id = meal.body()?.meals
                if (responseBody != null){
                    emit(NetworkResult.Success(responseBody!!))

                }else{
                    emit(NetworkResult.Error("Meal List Not Found"))
                }
            }else{
                Log.d("apiServiceError", "statusCode : ${meal.code()}, message : ${meal.message()}")
                emit(NetworkResult.Error("Failed to fetch data from server"))
            }
        }catch (err:Exception) {
            err.printStackTrace()
            Log.d("remoteError", "${err.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log"))
        }
    }
    suspend fun getIdMeal(idMeal:String):Flow<NetworkResult<ResponseIdMeal>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val mealDetail = mealApi.getIdMeal(idMeal.toInt())
            println("$idMeal adalah id yg dikirm ke api ")
            if (mealDetail.isSuccessful){
                val responseBody = mealDetail.body()

                if (responseBody != null){
                    emit(NetworkResult.Success(responseBody))
                    println("hasil nya $responseBody")
                }else{
                    emit(NetworkResult.Error("Meal id Not Found"))
                }
            }else{
                Log.d("apiServiceError",
                    "statusCode : ${mealDetail.code()}, message : ${mealDetail.message()}")
                    emit(NetworkResult.Error("Failed to fetch data id from server"))
            }
        }catch (err:Exception){
            err.printStackTrace()
            Log.d("remoteError", "${err.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log."))
        }
    }.flowOn(Dispatchers.IO)

}