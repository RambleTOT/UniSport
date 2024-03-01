package com.example.unisportinverse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentLoginBinding
import com.example.unisportinverse.databinding.FragmentSplashScreenBinding

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            backToOnboarding()
        }
        binding!!.buttonBackLogin.setOnClickListener{
            backToOnboarding()
        }
    }

    fun backToOnboarding(){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val onboardingFragment = OnBoardingFragment()
        transaction.replace(R.id.layout_fragment, onboardingFragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

}