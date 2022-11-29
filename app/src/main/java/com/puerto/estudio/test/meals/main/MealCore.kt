package com.puerto.estudio.test.meals.main

import com.puerto.estudio.test.meals.dto.Meal

interface MealCore {

    suspend fun getMealList() : Result<List<Meal>>
}