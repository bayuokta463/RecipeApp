package com.bayuokta.recipeapp.network

import com.bayuokta.recipeapp.model.Food
import com.bayuokta.recipeapp.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("foods")
    fun getFood():Call<List<Food>>

}
