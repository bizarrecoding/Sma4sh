package com.bizarrecoding.sm4sh.screens.catalogue

import androidx.lifecycle.*
import kotlinx.coroutines.*
import com.bizarrecoding.sm4sh.database.ProductRepository
import com.bizarrecoding.sm4sh.models.Brand
import com.bizarrecoding.sm4sh.models.Product

class CatalogueViewModel(private val repository: ProductRepository): ViewModel() {

    private val _selectedBrand = MutableLiveData<String>("All")
    val selectedBrand: LiveData<String>
        get() = _selectedBrand

    val products: LiveData<List<Product>>
        get() = Transformations.switchMap(selectedBrand){ selected->
            repository.observeProductsByBrand(selected)
        }

    val popular: LiveData<List<Product>>
        get() = repository.observePopular()

    val new: LiveData<List<Product>>
        get() = repository.observeNew()

    val brands: LiveData<List<Brand>>
        get() = repository.observeBrands()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            repository.refreshProducts()
        }
    }

    fun setBrand(brand: Brand) {
        _selectedBrand.value = brand.name
    }
}
