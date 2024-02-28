package com.example.unisportinverse.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.OnBoardingFragment
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null

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
        Handler().postDelayed(Runnable {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val loginFragment = OnBoardingFragment()
            transaction.replace(R.id.layout_fragment, loginFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }, 3000)
    }

}