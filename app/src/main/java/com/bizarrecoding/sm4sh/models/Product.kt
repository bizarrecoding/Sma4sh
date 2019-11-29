package com.bizarrecoding.sm4sh.models

import java.util.*

data class Product (
    val objectId: String,
    val name: String,
    val universe: String,
    val price: String,
    val imageURL: String,
    val kind: String,
    val popular: Boolean,
    val rating: String,
    val downloads: String,
    val description: String,
    val SKU: String,
    val createdAt: Date,
    val updatedAt: Date
)