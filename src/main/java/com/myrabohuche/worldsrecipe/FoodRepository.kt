package com.myrabohuche.worldsrecipe

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FoodRepository(
    private val foodDao: FoodDao,
    private val apiService: ApiService) {

    val food: LiveData<List<Food>> = foodDao.getFood()

    suspend fun refreshFoods() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called")
            val foodList = apiService.getApi().await()
            foodDao.clear()
            foodDao.upSert(foodList)
        }
    }
}


