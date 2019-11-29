package com.bizarrecoding.sm4sh.screens.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizarrecoding.sm4sh.models.Product

class ProductViewModel(producto: Product) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float>
        get() = _rating

    private val _downloads = MutableLiveData<Int>()
    val downloads: LiveData<Int>
        get() = _downloads


    init {
        _product.value = producto
        if(_rating.value!=null) {
            _rating.value = producto.rating?.toFloat()
        }else{
            _rating.value = 0.0f
        }
        if(_downloads.value!=null) {
            _downloads.value = producto.downloads?.toInt()
        }else{
            _downloads.value = 0
        }
    }
}
