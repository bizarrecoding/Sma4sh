package com.bizarrecoding.sm4sh.screens.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizarrecoding.sm4sh.database.ProductRepository
import com.bizarrecoding.sm4sh.models.Brand
import java.util.*

class PreferencesViewModel(private val repository: ProductRepository): ViewModel() {

    enum class Orders{
        DOWNLOADS,
        DATE,
        PRICE
    }
    val brands: LiveData<List<Brand>>
        get() = repository.observeBrands()

    private var _selectedBrand = MutableLiveData<String?>()
    val selectedBrand: LiveData<String?>
        get() = _selectedBrand

    private var _currentOrder = MutableLiveData<String>("DOWNLOADS")
    val currentOrder: LiveData<String>
        get() = _currentOrder

    private var _currentMin = MutableLiveData(0)
    val currentMin: LiveData<Int>
        get() = _currentMin

    private var _currentMax = MutableLiveData(100)
    val currentMax: LiveData<Int>
        get() = _currentMax

    private var _rating = MutableLiveData<List<Boolean>>(listOf(true,true,true,true,true))
    val rating: LiveData<List<Boolean>>
        get() = _rating

    fun setOrder(order: Orders) {
        _currentOrder.value = order.name
    }

    fun updateRatingFilter(checkedId: Int, isChecked: Boolean) {
        val a = _rating.value ?: Collections.emptyList()
        val current = arrayListOf(*a.toTypedArray())
        current.let {
            it[checkedId] = isChecked
        }
    }

}