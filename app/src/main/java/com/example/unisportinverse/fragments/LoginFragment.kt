package com.example.unisportinverse.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentLoginBinding


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
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.image_button_click)
            binding!!.buttonBackLogin.startAnimation(scaleDown)
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