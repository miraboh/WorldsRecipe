package com.myrabohuche.worldsrecipe

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upSert(food: List<Food>)

    @Query("SELECT * FROM world_food")
    fun getFood(): LiveData<List<Food>>

    @Query("DELETE FROM world_food where id = id")
    fun clear()
}

