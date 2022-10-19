package com.test.currentweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.test.currentweather.R
import com.test.currentweather.databinding.FragmentSettingsBinding
import com.test.currentweather.databinding.FragmentWeatherBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {


    private lateinit var mBinding: FragmentSettingsBinding
    lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        initViews()
        return mBinding.getRoot()
    }

    private fun initViews() {
        mNavController = findNavController(requireActivity(), R.id.nav_host_fragment)
        setupWithNavController(mBinding.tbSettings, mNavController)

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
//                Log.e(TAG, "HARD Back Pressed");
                    // Handle the back button event
                    mNavController.popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)


        mBinding.mbtgTemp.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val buttonName = when (checkedId) {
                R.id.mb_celsius -> "Button 1 (Horizontal)"
                R.id.mb_fahrenheit -> "Button 2 (Horizontal)"
                else -> ""
            }
            val text = if (isChecked) "Checked $buttonName" else "Unchecked $buttonName"
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}