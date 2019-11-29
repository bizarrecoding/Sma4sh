package com.bizarrecoding.sm4sh.Screens.Catalogue

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.bizarrecoding.sm4sh.R
import com.bizarrecoding.sm4sh.databinding.CatalogueFragmentBinding

class CatalogueFragment : Fragment() {

    private lateinit var catalogueViewModel: CatalogueViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val binding: CatalogueFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.catalogue_fragment,container,false)
        //connect to DB

        val catalogueViewModel = ViewModelProviders.of(this).get(CatalogueViewModel::class.java)
        binding.catalogueViewModel = catalogueViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }
}
