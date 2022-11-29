package com.puerto.estudio.test.meals.services.retrofit

import com.puerto.estudio.test.meals.dto.MealPageItems
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitThemealdbService {

    @GET("1/random.php")
    suspend fun getRandomMealPageItems(): Response<MealPageItems>
}