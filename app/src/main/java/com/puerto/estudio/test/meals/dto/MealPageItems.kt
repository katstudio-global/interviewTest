package com.puerto.estudio.test.meals.dto

import com.google.gson.annotations.SerializedName

data class MealPageItems(
    @SerializedName("meals")
    val meals: List<Meal>
)