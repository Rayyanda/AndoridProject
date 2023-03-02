package com.bootcamp.dhihyarayyanda_mealdb.data

import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealDao
import com.bootcamp.dhihyarayyanda_mealdb.data.database.MealEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: MealDao) {
    //menambah list favorite
    suspend fun insertMeal(mealEntity: MealEntity) = dao.insertMeal(mealEntity)
    //untuk menampilkan list favorite
    fun listMeal():Flow<List<MealEntity>> = dao.listMeal()
    //menghapus makanan dari list favorite
    suspend fun deleteMeal(mealEntity: MealEntity) = dao.deleteMeal(mealEntity)

    suspend fun deleteAllMeal() = dao.deleteAllMeal()

}