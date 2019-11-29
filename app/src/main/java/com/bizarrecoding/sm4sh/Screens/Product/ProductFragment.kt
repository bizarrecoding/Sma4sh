package com.bizarrecoding.sm4sh.Screens.Product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.bizarrecoding.sm4sh.R
import com.bizarrecoding.sm4sh.databinding.ProductFragmentBinding
import com.bizarrecoding.sm4sh.models.Product

class ProductFragment : Fragment() {


    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {
        val binding: ProductFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.catalogue_fragment,container,false)
        //connect to DB

        val catalogueViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        binding.productViewModel = productViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }
}
