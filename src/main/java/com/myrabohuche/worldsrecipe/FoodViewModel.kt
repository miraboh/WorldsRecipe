package com.myrabohuche.worldsrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.io.IOException

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    val foodlist: LiveData<List<Food>> = foodRepository.food

    fun refreshAllList(){
        refreshViewModel()
       refreshDataFromRepository()
    }

    private fun refreshViewModel(){
        super.onCleared()
        SupervisorJob().cancelChildren()
    }

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                foodRepository.refreshFoods()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                    _eventNetworkError.value = true
                    _isNetworkErrorShown.value = true
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


}
