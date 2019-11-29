package com.bizarrecoding.sm4sh.screens.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizarrecoding.sm4sh.models.Product
import com.bizarrecoding.sm4sh.smashApi.SmashAPI
import kotlinx.coroutines.*

class CatalogueViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _popular = MutableLiveData<List<Product>>()
    val popular: LiveData<List<Product>>
        get() = _popular

    private val _new = MutableLiveData<List<Product>>()
    val new: LiveData<List<Product>>
        get() = _new

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
    private val coroutineScope = CoroutineScope(catalogueViewModelJob + Dispatchers.Main)

    init {
        _total.value = 0
        _totalPopular.value = 0
        _totalNew.value = 0
        getCatalogue()
    }

    private fun getCatalogue(){
        coroutineScope.launch {
            val currentProduct = SmashAPI.service.GetProducts()
            _products.value = currentProduct.results
            _total.value = currentProduct.results.size
            val popular = currentProduct.results.filter {
                product -> product.popular!=null && product.popular
            }
            _popular.value = popular
            _totalPopular.value = popular.size
            val new = currentProduct.results.sortedBy {
                    product -> product.createdAt
            }.subList(0,5)
            _new.value = new
            _totalNew.value = new.size
        }
    }

    override fun onCleared() {
        super.onCleared()
        catalogueViewModelJob.cancel()
    }
}
