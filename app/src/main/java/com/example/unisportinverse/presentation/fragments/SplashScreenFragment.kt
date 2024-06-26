package com.example.unisportinverse.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentSplashScreenBinding
import com.example.unisportinverse.presentation.managers.FirstEntryManager

class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null
    private lateinit var firstEntryManager: FirstEntryManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstEntryManager = FirstEntryManager(requireActivity())
        Log.d("MyLog", firstEntryManager.getFirstEntry().toString())
        if (firstEntryManager.getFirstEntry() == false){
            Handler().postDelayed(Runnable {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val bottomNavBarFragment = BottomNavBarFragment()
                transaction.replace(R.id.layout_fragment, bottomNavBarFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }, 3000)
        }else{
            Handler().postDelayed(Runnable {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val onboardingFragment = OnBoardingFragment()
                transaction.replace(R.id.layout_fragment, onboardingFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }, 3000)
        }
    }

}