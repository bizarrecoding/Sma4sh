package com.bizarrecoding.sm4sh.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bizarrecoding.sm4sh.R
import kotlinx.android.synthetic.main.fragment_onboarding.*

private const val PAGES = 3

class OnBoardingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter = SlidePagerAdapter(this.childFragmentManager)
    }

    private inner class SlidePagerAdapter(fragmentManager: FragmentManager):
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        override fun getCount(): Int = PAGES

        override fun getItem(position: Int): Fragment {
            return StepFragment(position, View.OnClickListener { pager.currentItem = pager.currentItem+1 % PAGES})
        }
    }
}


