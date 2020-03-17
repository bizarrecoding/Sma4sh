package com.bizarrecoding.sm4sh.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bizarrecoding.sm4sh.R
import com.bizarrecoding.sm4sh.di.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SplashFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val factory: ViewModelFactory by instance()

    private val viewModel: SplashViewModel by lazy{
        ViewModelProvider(this, factory).get(SplashViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskDone.observe(viewLifecycleOwner, Observer { finished ->
            if(finished){
                GlobalScope.launch {
                    delay(100)
                    val action = if(viewModel.skip.value!=null && viewModel.skip.value==true){
                        SplashFragmentDirections.skipToCatalogue()
                    }else{
                        SplashFragmentDirections.toOnBoarding()
                    }
                    findNavController().navigate(action)
                }
            }
        })
    }
}