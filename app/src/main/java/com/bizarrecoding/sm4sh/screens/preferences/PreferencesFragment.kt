package com.bizarrecoding.sm4sh.screens.preferences

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bizarrecoding.sm4sh.R
import com.bizarrecoding.sm4sh.adapters.UniverseFilterAdapter
import com.bizarrecoding.sm4sh.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_preferences.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class PreferencesFragment : Fragment(), KodeinAware, CompoundButton.OnCheckedChangeListener {

    override val kodein by closestKodein()
    private val factory: ViewModelFactory by instance()

    private val preferencesViewModel: PreferencesViewModel by lazy{
        ViewModelProvider(this, factory).get(PreferencesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        universeList.apply {
            //choiceMode = ListView.CHOICE_MODE_SINGLE
            universeList.adapter = UniverseFilterAdapter(UniverseFilterAdapter.OnClickListener { brand->
                Log.d("HRK_FILTER", brand.name ?: "null")
            })
        }
        preferencesViewModel.brands.observe(viewLifecycleOwner, Observer {
            with(universeList.adapter as UniverseFilterAdapter) {
                submitList(it)
                notifyDataSetChanged()
            }
        })
        backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        orderFilter.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.orderDownloads ->  preferencesViewModel.setOrder(PreferencesViewModel.Orders.DOWNLOADS)
                R.id.orderDate->        preferencesViewModel.setOrder(PreferencesViewModel.Orders.DATE)
                R.id.orderPrice->       preferencesViewModel.setOrder(PreferencesViewModel.Orders.PRICE)
            }
        }
        rated1.setOnCheckedChangeListener(this)
        rated2.setOnCheckedChangeListener(this)
        rated3.setOnCheckedChangeListener(this)
        rated4.setOnCheckedChangeListener(this)
        rated5.setOnCheckedChangeListener(this)
        apply.setOnClickListener {
            val action = PreferencesFragmentDirections.toFilteredCatalogue()
            action.arguments.apply {
                putString("ORDER",preferencesViewModel.currentOrder.value)
                putInt("MIN",preferencesViewModel.currentMin.value!!)
                putInt("MAX",preferencesViewModel.currentMax.value!!)
                putBooleanArray("RATING",preferencesViewModel.rating.value?.toBooleanArray())
                if(preferencesViewModel.selectedBrand.value!=null){
                    putString("UNIVERSE",preferencesViewModel.selectedBrand.value)
                }
            }
            findNavController().navigate(action)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
       when(buttonView!!.id){
           R.id.rated1 -> preferencesViewModel.updateRatingFilter(0, isChecked)
           R.id.rated2 -> preferencesViewModel.updateRatingFilter(1, isChecked)
           R.id.rated3 -> preferencesViewModel.updateRatingFilter(2, isChecked)
           R.id.rated4 -> preferencesViewModel.updateRatingFilter(3, isChecked)
           R.id.rated5 -> preferencesViewModel.updateRatingFilter(4, isChecked)
       }
    }
}
