package com.bootcamp.dhihyarayyanda_mealdb.data.database


import android.os.Parcelable
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bootcamp.dhihyarayyanda_mealdb.model.MealsItem2
import com.bootcamp.dhihyarayyanda_mealdb.model.ResponseIdMeal
import com.bootcamp.dhihyarayyanda_mealdb.utils.Constant.MEAL_TABLE
import kotlinx.parcelize.Parcelize


@Entity(tableName = MEAL_TABLE)
@Parcelize
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    //val meals : MealsItem2,
    var strMeal : String,
    val strMealThumb :String,
    val strArea :String,
    val strInstruction :String
): Parcelable
