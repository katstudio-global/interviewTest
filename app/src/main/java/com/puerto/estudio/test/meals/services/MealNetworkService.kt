package com.puerto.estudio.test.meals.services

import com.puerto.estudio.test.meals.dto.MealPageItems

interface MealNetworkService {

    suspend fun getRandomMealPageItems(): Result<MealPageItems>
}