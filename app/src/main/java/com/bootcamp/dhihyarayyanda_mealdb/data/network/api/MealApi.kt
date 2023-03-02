package com.bootcamp.dhihyarayyanda_mealdb.data.network.api

import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseIdMeal
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseMeal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
        
        @GET("filter.php?c=Seafood")
        suspend fun getMeal(
                @Query("c") category : String
        ):Response<ResponseMeal>

        @GET("lookup.php")
        suspend fun getIdMeal(
                @Query("i") i : Int
        ):Response<ResponseIdMeal>
}