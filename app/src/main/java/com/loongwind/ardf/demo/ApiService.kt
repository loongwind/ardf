package com.loongwind.ardf.demo

import retrofit2.http.GET

interface ApiService {

    @GET("user")
    suspend fun getUser():User
}