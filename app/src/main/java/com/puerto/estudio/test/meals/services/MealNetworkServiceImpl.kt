package com.puerto.estudio.test.meals.services

import com.puerto.estudio.test.meals.dto.MealPageItems
import com.puerto.estudio.test.meals.services.retrofit.RetrofitThemealdbService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealNetworkServiceImpl : MealNetworkService {

    private val baseUrlService: String = "https://www.themealdb.com/api/json/v1/"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrlService)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override suspend fun getRandomMealPageItems(): Result<MealPageItems> {
        return try {
            val retrofitThemealdbService = retrofit.create(RetrofitThemealdbService::class.java)
            val randomMealPageItems = retrofitThemealdbService.getRandomMealPageItems()
            Result.success(randomMealPageItems.body()!!)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}