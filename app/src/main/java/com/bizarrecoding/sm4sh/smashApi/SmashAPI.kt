package com.bizarrecoding.sm4sh.smashApi

import com.bizarrecoding.sm4sh.models.Product
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SmashAPI {
    companion object{
        private const val BASE_URL="https://parseapi.back4app.com/"
        var SmashClient: SmashAPI? = null
        fun GetClient(): SmashAPI?{
            if(SmashClient==null){
                SmashClient = Retrofit.Builder()
                    .baseUrl(HttpUrl.parse(BASE_URL)!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SmashAPI::class.java)
            }
            return SmashClient
        }
    }

    @GET("classes/Product")
    fun GetProducts(): Call<List<Product>>

}