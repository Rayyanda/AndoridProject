package com.bootcamp.dhihyarayyanda_mealdb.data.database

import androidx.room.TypeConverter

import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun mealDataToString(meal : MealsItem2):String{
        return gson.toJson(meal)
    }

    @TypeConverter
    fun mealStringToData(string: String):MealsItem2{
        val listType = object : TypeToken<MealsItem2>(){}.type
        return gson.fromJson(string,listType)
    }
}