package com.bizarrecoding.sm4sh.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.Date


@Parcelize
@Entity(tableName = "Products")
data class Product (
    @PrimaryKey
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
) : Parcelable
