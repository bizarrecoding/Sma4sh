package com.bizarrecoding.sm4sh.screens.catalogue

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.bizarrecoding.sm4sh.adapters.ProductAdapter
import com.bizarrecoding.sm4sh.databinding.CatalogueFragmentBinding

class CatalogueFragment : Fragment() {

    private val catalogueViewModel: CatalogueViewModel by lazy{
        ViewModelProviders.of(this).get(CatalogueViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val binding: CatalogueFragmentBinding = CatalogueFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.catalogueViewModel = this.catalogueViewModel

        binding.newGrid.adapter = ProductAdapter(ProductAdapter.OnClickListener { product->
            val action = CatalogueFragmentDirections.actionCatalogueFragmentToProductFragment(product)
            this.findNavController().navigate(action)
        })
        binding.popularGrid.adapter = ProductAdapter(ProductAdapter.OnClickListener {product->
            val action = CatalogueFragmentDirections.actionCatalogueFragmentToProductFragment(product)
            this.findNavController().navigate(action)
        })

        binding.productsGrid.adapter = ProductAdapter(ProductAdapter.OnClickListener {product->
            val action = CatalogueFragmentDirections.actionCatalogueFragmentToProductFragment(product)
            this.findNavController().navigate(action)
        })

        return binding.root
    }
}
