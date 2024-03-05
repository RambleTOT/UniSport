package com.example.unisportinverse.fragments

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.FragmentLoginBinding
import com.example.unisportinverse.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var binding: FragmentRegistrationBinding? = null
    private var currentScreen = 0
    private var name = ""
    private var phone = ""
    private var password = ""
    private var clickImage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            backToOnboarding()
        }
        binding!!.buttonBackRegistration.setOnClickListener{
            backToOnboarding()
        }
        binding!!.buttonRegistrationNext.setOnClickListener{
            currentScreen++
            plusCurrentScreen()
        }
        binding!!.imageChild.setOnClickListener {
            clickImage = 1
            changeCheckBox()
        }
        binding!!.imageParent.setOnClickListener {
            clickImage = 2
            changeCheckBox()
        }
        plusCurrentScreen()
    }

    fun backToOnboarding(){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val onboardingFragment = OnBoardingFragment()
        transaction.replace(R.id.layout_fragment, onboardingFragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    fun changeCheckBox(){
        when(clickImage){
            1 -> {
                binding!!.checkboxImageChild.visibility = View.VISIBLE
                binding!!.checkboxImageParent.visibility = View.GONE
            }
            2 -> {
                binding!!.checkboxImageChild.visibility = View.GONE
                binding!!.checkboxImageParent.visibility = View.VISIBLE
            }
        }
    }

    fun plusCurrentScreen(){
        when(currentScreen){
            0 -> {
                binding!!.editTextName.hint = getString(R.string.hint_registration_name)
                binding!!.textRegTitle.text = getString(R.string.title_registration_name)
                binding!!.editTextName.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            }
            1 -> {
                binding!!.editTextName.hint = getString(R.string.hint_registration_phone)
                binding!!.editTextName.inputType = InputType.TYPE_CLASS_PHONE
                binding!!.textRegTitle.text = getString(R.string.title_registration_phone)
            }
            2 -> {
                binding!!.editTextName.hint = getString(R.string.hint_registration_password)
                binding!!.editTextName.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding!!.textRegTitle.text = getString(R.string.title_registration_password)
            }
            3 -> {
                binding!!.registrationEditTextName.visibility = View.GONE
                binding!!.linearRole.visibility = View.VISIBLE
                binding!!.textRegTitle.text = getString(R.string.title_registration_role)
            }
        }
    }

}