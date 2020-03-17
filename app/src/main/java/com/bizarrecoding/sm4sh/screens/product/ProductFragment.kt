package com.bizarrecoding.sm4sh.screens.product

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bizarrecoding.sm4sh.di.ViewModelFactory
import com.bizarrecoding.sm4sh.databinding.ProductFragmentBinding
import kotlinx.android.synthetic.main.product_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ProductFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val factory: ViewModelFactory by instance()

    private val productViewModel: ProductViewModel by lazy{
        ViewModelProvider(this, factory).get(ProductViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val binding = ProductFragmentBinding.inflate(inflater)
        val product = ProductFragmentArgs.fromBundle(arguments!!).product
        productViewModel.setProduct(product)
        binding.product = productViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        share.apply {
            setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey look out "+productViewModel.product.value?.name+" at Sm4sh")
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent,"send to"))
            }
        }
        back.apply {
            setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}
