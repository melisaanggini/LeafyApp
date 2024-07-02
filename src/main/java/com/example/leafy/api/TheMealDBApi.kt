package com.example.leafy.api

import com.example.leafy.model.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBApi {
    @GET("search.php")
    fun searchMeals(@Query("s") query: String): Call<MealResponse>
}
