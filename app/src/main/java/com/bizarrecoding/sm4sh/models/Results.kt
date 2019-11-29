package com.bizarrecoding.sm4sh.models

import com.squareup.moshi.Json


data class Results (
    @Json(name = "results")
    val results: List<Product>
)
