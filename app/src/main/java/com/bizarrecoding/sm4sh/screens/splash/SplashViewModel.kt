package com.bizarrecoding.sm4sh.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizarrecoding.sm4sh.database.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: ProductRepository): ViewModel() {

    private val _taskDone = MutableLiveData(false)
    val taskDone: LiveData<Boolean>
        get() = _taskDone

    private val _skip = MutableLiveData(false)
    val skip: LiveData<Boolean>
        get() = _skip

    init {
        viewModelScope.launch(Dispatchers.IO){
            if(repository.loadConfig()>0){
                _skip.postValue(true)
            }
            repository.refreshProducts()
            _taskDone.postValue(true)
        }
    }
}