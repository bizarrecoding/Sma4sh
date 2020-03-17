package com.bizarrecoding.sm4sh.screens.catalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bizarrecoding.sm4sh.R
import com.bizarrecoding.sm4sh.di.ViewModelFactory
import com.bizarrecoding.sm4sh.adapters.BrandAdapter
import com.bizarrecoding.sm4sh.adapters.NewProductAdapter
import com.bizarrecoding.sm4sh.adapters.PopularProductAdapter
import com.bizarrecoding.sm4sh.adapters.ProductAdapter
import com.bizarrecoding.sm4sh.databinding.FragmentCatalogueBinding
import kotlinx.android.synthetic.main.fragment_catalogue.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CatalogueFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val factory: ViewModelFactory by instance()

    private val catalogueViewModel: CatalogueViewModel by lazy{
        ViewModelProvider(this, factory).get(CatalogueViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val binding: FragmentCatalogueBinding = FragmentCatalogueBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.catalogueViewModel = catalogueViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewListeners()
        setDataObserver()
    }

    private fun setViewListeners() {
        filters.setOnClickListener {
            val action = CatalogueFragmentDirections.toFilters()
            this.findNavController().navigate(action)
        }
        newGrid.adapter = NewProductAdapter(NewProductAdapter.OnClickListener { product->
            val action = CatalogueFragmentDirections.toProductDetails(product)
            this.findNavController().navigate(action)
        })
        popularGrid.adapter = PopularProductAdapter(PopularProductAdapter.OnClickListener {product->
            val action = CatalogueFragmentDirections.toProductDetails(product)
            this.findNavController().navigate(action)
        })
        productsGrid.adapter = ProductAdapter(ProductAdapter.OnClickListener {product->
            val action = CatalogueFragmentDirections.toProductDetails(product)
            this.findNavController().navigate(action)
        })
        brandList.adapter = BrandAdapter(BrandAdapter.OnClickListener{ brand ->
            catalogueViewModel.setBrand(brand)
        })
    }

    private fun setDataObserver(){
        catalogueViewModel.run {
            brands.observe(viewLifecycleOwner, Observer {
                with(brandList.adapter as BrandAdapter){
                    submitList(it)
                    notifyDataSetChanged()
                }
            })
            products.observe(viewLifecycleOwner, Observer {
                allLabel.text = resources.getString(R.string.total_format, selectedBrand.value, it.size)
                with(productsGrid.adapter as ProductAdapter){
                    submitList(it)
                    notifyDataSetChanged()
                }
            })
            new.observe(viewLifecycleOwner, Observer {
                newLabel.text = resources.getString(R.string.new_format, it.size)
                with(newGrid.adapter as NewProductAdapter){
                    submitList(it)
                    notifyDataSetChanged()
                }
            })
            popular.observe(viewLifecycleOwner, Observer {
                popularLabel.text = resources.getString(R.string.popular_format, it.size)
                with(popularGrid.adapter as PopularProductAdapter){
                    submitList(it)
                    notifyDataSetChanged()
                }
            })
        }
    }
}
