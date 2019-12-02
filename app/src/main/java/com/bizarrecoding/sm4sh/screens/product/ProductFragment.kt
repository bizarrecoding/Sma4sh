package com.bizarrecoding.sm4sh.screens.product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bizarrecoding.sm4sh.databinding.ProductFragmentBinding

class ProductFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {
        val binding = ProductFragmentBinding.inflate(inflater)
        val productBundle = ProductFragmentArgs.fromBundle(arguments!!).productos
        val productViewModelFactory = ProductViewModelFactory(productBundle)
        val productViewModel = ViewModelProviders
            .of(this,productViewModelFactory)
            .get(ProductViewModel::class.java)
        binding.product = productViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
