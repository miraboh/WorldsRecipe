package com.myrabohuche.worldsrecipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "world_food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val pics: String,
    val origin: String,
    val ingredients: String,
    val preparation: String
)