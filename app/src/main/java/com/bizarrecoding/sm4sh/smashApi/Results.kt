package com.bizarrecoding.sm4sh.smashApi

import com.bizarrecoding.sm4sh.models.Product
import java.util.Date
import com.squareup.moshi.Json

data class Results (
    @Json(name = "results")
    val results: List<NetworkProduct>
)

data class NetworkProduct (
    val objectId: String,
    val name: String?,
    val universe: String?,
    val price: String?,
    val imageURL: String?,
    val kind: String?,
    val popular: Boolean?,
    val rating: String?,
    val downloads: String?,
    val description: String?,
    val SKU: String?,
    val createdAt: Date,
    val updatedAt: Date
)

fun Results.asDatabaseModel(): Array<Product>{
    return results.filter {
        !it.name.isNullOrBlank()
    }.map{
        Product(
            objectId = it.objectId,
            name = it.name!!,
            universe = it.universe,
            price = it.price,
            imageURL = it.imageURL,
            kind = it.kind,
            popular = if (it.popular!=null && it.popular) 1 else 0,
            rating = it.rating,
            downloads = it.downloads,
            description = it.description,
            SKU = it.SKU,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }.toTypedArray()
}