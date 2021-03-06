package com.bizarrecoding.sm4sh.smashApi

import com.bizarrecoding.sm4sh.BuildConfig
import com.bizarrecoding.sm4sh.models.Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.Date


private const val BASE_URL="https://parseapi.back4app.com/"

private val moshi = Moshi.Builder()
    .add(Date::class.java, Rfc3339DateJsonAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

private val smashService = Retrofit.Builder()
    .baseUrl(HttpUrl.parse(BASE_URL)!!)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface SmashApiService {
    @Headers(
        "X-Parse-Application-Id: I9pG8SLhTzFA0ImFkXsEvQfXMYyn0MgDBNg10Aps",
        "X-Parse-REST-API-Key: Yvd2eK2LODfwVmkjQVNzFXwd3N0X7oUuwiMI3VDZ"
    )
    @GET("classes/Product")
    suspend fun GetProducts(): Results
}

object SmashAPI{
    val service: SmashApiService by lazy {
        smashService.create(SmashApiService::class.java)
    }
}