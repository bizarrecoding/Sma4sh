package com.bizarrecoding.sm4sh.database

import com.bizarrecoding.sm4sh.database.Product
import com.squareup.moshi.Json


data class Results (
    @Json(name = "results")
    val results: List<Product>
)
