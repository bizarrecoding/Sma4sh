package com.bizarrecoding.sm4sh.screens.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizarrecoding.sm4sh.models.Product

class ProductViewModel : ViewModel() {
    private val _product = MutableLiveData<Product?>()
    val product: LiveData<Product?>
        get() = _product

    private val _rating = MutableLiveData<Float>(0f)
    val rating: LiveData<Float>
        get() = _rating

    private val _downloads = MutableLiveData(0)
    val downloads: LiveData<Int>
        get() = _downloads

    fun setProduct(product: Product) {
        _product.value = product
        _rating.value = product.rating?.toFloat()
        _downloads.value = product.downloads?.toInt()
    }
}
