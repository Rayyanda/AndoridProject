package com.bootcamp.dhihyarayyanda_mealdb.data.network

import com.bootcamp.dhihyarayyanda_mealdb.data.network.api.MealApi
import com.bootcamp.dhihyarayyanda_mealdb.utils.Constant.Base_Url
import com.bootcamp.dhihyarayyanda_mealdb.utils.Constant.Base_Url_id
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object Service {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val MealService:MealApi by lazy {
        retrofit.create(MealApi::class.java)
    }

    val MealId:MealApi by lazy {
        retrofit.create(MealApi::class.java)
    }
}








