package com.bizarrecoding.sm4sh.screens.onboarding

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bizarrecoding.sm4sh.R
import kotlinx.android.synthetic.main.fragment_onboarding_step.*

class StepFragment(private val position: Int, private val listener: View.OnClickListener) : Fragment() {
    enum class Image{
        LINK,
        CHAR,
        LMAC
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stepMessage.text = resources.getStringArray(R.array.steps)[position]
        stepBtn.setOnClickListener(listener)
        if(position==2){
            stepBtn.text = resources.getText(R.string.get_started)
            stepBtn.background = resources.getDrawable(R.drawable.start_btn,null)
            stepBtn.setOnClickListener {
                val action = OnBoardingFragmentDirections.toCatalogue()
                findNavController().navigate(action)
            }
        }
        val cover = when(position){
            Image.LINK.ordinal -> resources.getDrawable(R.drawable.link,null)
            Image.CHAR.ordinal -> resources.getDrawable(R.drawable.lizardon,null)
            Image.LMAC.ordinal -> resources.getDrawable(R.drawable.lmac,null)
            else -> throw Resources.NotFoundException()
        }
        stepCover.setImageDrawable(cover)
        stepCover.left = 100
    }
}
