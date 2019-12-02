package com.bizarrecoding.sm4sh.screens.catalogue

import android.app.Application
import androidx.lifecycle.*
import com.bizarrecoding.sm4sh.database.SmashDao
import com.bizarrecoding.sm4sh.smashApi.SmashAPI
import kotlinx.coroutines.*


class CatalogueViewModel(val database: SmashDao, app: Application)
    : AndroidViewModel(app) {

    val products = database.getProducts()
    val popular = database.getPopularProducts()
    val new = database.getNewProducts()

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int>
        get() = _total

    private val _totalNew = MutableLiveData<Int>()
    val totalNew: LiveData<Int>
        get() = _totalNew

    private val _totalPopular = MutableLiveData<Int>()
    val totalPopular: LiveData<Int>
        get() = _totalPopular


    private var catalogueViewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + catalogueViewModelJob)

    init {
        coroutineScope.launch {
            getCatalogue()
        }
    }

    private suspend fun getCatalogue(){
        withContext(Dispatchers.IO){
            val currentProducts = SmashAPI.service.GetProducts()
            database.clear()
            for (product in currentProducts.results){
                if (!product.name.isNullOrBlank()){
                    database.insertProducts(product)
                }
            }
            if(products.value != null) {
                _total.postValue(products.value!!.size)
            } else{
                _total.postValue(0)
            }
            if(popular.value != null) {
                _totalPopular.postValue(popular.value!!.size)
            } else{
                _totalPopular.postValue(0)
            }
            if(new.value != null) {
                _totalNew.postValue(new.value!!.size)
            } else{
                _totalNew.postValue(0)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        catalogueViewModelJob.cancel()
    }
}
