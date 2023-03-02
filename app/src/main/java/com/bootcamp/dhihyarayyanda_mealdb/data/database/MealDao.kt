package com.bootcamp.dhihyarayyanda_mealdb.data.database

import kotlinx.coroutines.flow.Flow
import androidx.room.*


@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealEntity: MealEntity)

    @Query("SELECT * FROM seafood_meal")
    fun listMeal(): Flow<List<MealEntity>>

    @Delete()
    suspend fun deleteMeal(mealEntity: MealEntity)

    @Query("DELETE FROM seafood_meal")
    suspend fun deleteAllMeal()
}