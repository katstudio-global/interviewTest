package com.puerto.estudio.test.meals.main

import com.puerto.estudio.test.meals.dto.Meal
import com.puerto.estudio.test.meals.dto.MealPageItems
import com.puerto.estudio.test.meals.services.MealNetworkService

class MealCoreImpl(private val mealNetworkService: MealNetworkService) : MealCore {

    private val listCount = 20

    //Esto se podría optimizar (tiempo) utilizando multithread
    override suspend fun getMealList(): Result<List<Meal>> {
        val mealHash = HashMap<String, Meal>()
        while (mealHash.size < listCount) {
            val randomMealPageItems = mealNetworkService.getRandomMealPageItems()
            if (randomMealPageItems.isFailure)
                return Result.failure(randomMealPageItems.exceptionOrNull()!!)
            val mealPageItems = randomMealPageItems.getOrNull()
            if (existsMealItemInMap(mealPageItems, mealHash))
                continue
            putMealItemInMap(mealPageItems!!, mealHash)
        }
        return Result.success(mealHash.map { entry -> entry.value })
    }

    private fun existsMealItemInMap(
        mealPageItems: MealPageItems?,
        map: HashMap<String, Meal>
    ): Boolean {

        if (mealPageItems == null || mealPageItems.meals.isEmpty())
            return true
        return map.containsKey(mealPageItems.meals[0].id)
    }

    private fun putMealItemInMap(mealPageItems: MealPageItems, map: HashMap<String, Meal>) {

        if (mealPageItems.meals.isEmpty())
            return

        val meal = mealPageItems.meals[0]
        map[meal.id] = meal
    }
}