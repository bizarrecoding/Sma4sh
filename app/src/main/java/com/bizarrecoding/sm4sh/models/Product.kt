package com.bizarrecoding.sm4sh.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Product (
    @Json(name = "objectId")
    val objectId: String,

    @Json(name = "name")
    val name: String?,

    @Json(name = "universe")
    val universe: String?,

    @Json(name = "price")
    val price: String?,

    @Json(name = "imageURL")
    val imageURL: String?,

    @Json(name = "kind")
    val kind: String?,

    @Json(name = "popular")
    val popular: Boolean?,

    @Json(name = "rating")
    val rating: String?,

    @Json(name = "downloads")
    val downloads: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "SKU")
    val SKU: String?,

    @Json(name = "createdAt")
    val createdAt: Date,

    @Json(name = "updatedAt")
    val updatedAt: Date
) : Parcelable {
    val isNew
        get() = createdAt.before(updatedAt)
}
