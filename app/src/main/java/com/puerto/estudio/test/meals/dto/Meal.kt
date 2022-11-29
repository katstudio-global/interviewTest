package com.puerto.estudio.test.meals.dto

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strArea")
    val origin: String,
    @SerializedName("strYoutube")
    val youtubeVideo: String?,
    @SerializedName("strMealThumb")
    val photo: String
)
