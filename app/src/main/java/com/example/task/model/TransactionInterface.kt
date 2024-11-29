package com.example.assessment.model

import com.example.task.model.TransactionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TransactionInterface {
    @GET("users")
    fun getUsers(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<List<TransactionData>>




}