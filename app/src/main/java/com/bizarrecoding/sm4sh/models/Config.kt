package com.bizarrecoding.sm4sh.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "config")
data class Config (
    @PrimaryKey
    val name: String = "",
    val value: Int = 0
)