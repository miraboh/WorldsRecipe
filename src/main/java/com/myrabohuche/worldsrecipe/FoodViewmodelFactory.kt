package com.myrabohuche.worldsrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class FoodViewmodelFactory (private val foodRepository: FoodRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FoodViewModel(foodRepository) as T
    }
}