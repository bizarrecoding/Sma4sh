package com.bizarrecoding.sm4sh.screens.catalogue


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bizarrecoding.sm4sh.adapters.NewProductAdapter
import com.bizarrecoding.sm4sh.adapters.PopularProductAdapter

import com.bizarrecoding.sm4sh.adapters.ProductAdapter
import com.bizarrecoding.sm4sh.database.SmashDatabase
import com.bizarrecoding.sm4sh.databinding.CatalogueFragmentBinding

class CatalogueFragment : Fragment() {

    private val catalogueViewModel: CatalogueViewModel by lazy{
        val application = requireNotNull(this.activity).application
        val dataSource = SmashDatabase.getInstance(application).smashDao
        val viewModelFactory = CatalogueViewModelFactory(dataSource,application)
        ViewModelProviders.of(this, viewModelFactory).get(CatalogueViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val binding: CatalogueFragmentBinding = CatalogueFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.catalogueViewModel = catalogueViewModel

        binding.newGrid.adapter = NewProductAdapter(NewProductAdapter.OnClickListener { product->
            val action = CatalogueFragmentDirections.actionCatalogueFragmentToProductFragment(product)
            this.findNavController().navigate(action)
        })
        binding.popularGrid.adapter = PopularProductAdapter(PopularProductAdapter.OnClickListener {product->
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
