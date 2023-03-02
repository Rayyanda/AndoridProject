package com.bootcamp.dhihyarayyanda_mealdb.api

import com.bootcamp.dhihyarayyanda_mealdb.data.network.api.MealApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    /*const val Base_Url = ("https://www.themealdb.com/api/json/")

    private fun apiKeyINterceptor():OkHttpClient{
        return OkHttpClient().Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url().newBuilder()
                    .addQueryParameter("apiKey",)
            }
    }

    fun getService(): MealApi {
        return getRetrofit().create(MealApi::class.java)
    }*/

}