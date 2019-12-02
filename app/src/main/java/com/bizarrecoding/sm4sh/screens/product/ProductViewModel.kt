package com.bizarrecoding.sm4sh.screens.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizarrecoding.sm4sh.database.Product

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
        if(producto.rating!=null) {
            Log.d("Producto-rating:",producto.rating)
            _rating.value = producto.rating.toFloat()
        }else{
            _rating.value = 0.0f
        }
        if(producto.downloads!=null) {
            Log.d("Producto-descargas:",producto.downloads)
            _downloads.value = producto.downloads.toInt()
        }else{
            _downloads.value = 0
        }
    }
}
