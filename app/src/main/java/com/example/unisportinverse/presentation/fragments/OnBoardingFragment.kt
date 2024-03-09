package com.example.unisportinverse.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.R
import com.example.unisportinverse.presentation.adapters.ViewPagerOnboardingAdapter
import com.example.unisportinverse.databinding.FragmentFirstOnboardingBinding
import com.example.unisportinverse.databinding.FragmentOnBoardingBinding
import com.example.unisportinverse.databinding.FragmentSplashScreenBinding


class OnBoardingFragment : Fragment() {

    private var binding: FragmentOnBoardingBinding      ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding!!.root

        val fragmentList = arrayListOf(
            FirstOnboardingFragment(),
            SecondOnboardingFragment(),
            ThirdOnboardingFragment(),
            FourthOnboardingFragment()
        )

        val adapter = ViewPagerOnboardingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = binding!!.viewPagerOnboarding
        viewPager.adapter = adapter
        val indicator = binding!!.dotsIndicatorOnboarding

        indicator.attachTo(viewPager)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonToLogin.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val loginFragment = LoginFragment()
            transaction.replace(R.id.layout_fragment, loginFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonToRegistration.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val registrationFragment = RegistrationFragment()
            transaction.replace(R.id.layout_fragment, registrationFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}