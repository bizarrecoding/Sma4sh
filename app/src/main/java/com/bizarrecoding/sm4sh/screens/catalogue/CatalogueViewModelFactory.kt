package com.bizarrecoding.sm4sh.screens.catalogue

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bizarrecoding.sm4sh.database.SmashDao

@Suppress("UNCHECKED_CAST")
class CatalogueViewModelFactory(private val dataSource: SmashDao, private val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogueViewModel::class.java)) {
            return CatalogueViewModel(dataSource,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}