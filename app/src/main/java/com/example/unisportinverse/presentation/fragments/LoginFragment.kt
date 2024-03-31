package com.example.unisportinverse.presentation.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.unisportinverse.R
import com.example.unisportinverse.data.model.GetTokenResponse
import com.example.unisportinverse.data.model.UserLoginEntity
import com.example.unisportinverse.databinding.FragmentLoginBinding
import com.example.unisportinverse.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private var isEmptyPhone = false
    private var isEmptyPassword = false

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
        initEditText()
        binding!!.buttonBackLogin.setOnClickListener{
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.image_button_click)
            binding!!.buttonBackLogin.startAnimation(scaleDown)
            backToOnboarding()
        }
        binding!!.textToRegistration.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val registrationFragment = RegistrationFragment()
            transaction.replace(R.id.layout_fragment, registrationFragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
        binding!!.buttonLogin.setOnClickListener {
            login(binding!!.editTextPhoneNumber.text.toString(),
                binding!!.editTextPassword.text.toString()
            )
        }
    }

    fun initEditText(){
        binding!!.editTextPassword.setOnClickListener {
            binding!!.textErrorLogin.visibility = View.GONE
        }

        binding!!.editTextPhoneNumber.setOnClickListener {
            binding!!.textErrorLogin.visibility = View.GONE
        }

        binding!!.editTextPhoneNumber.addTextChangedListener(object : TextWatcher {
            // when there is no text added
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyPhone = false
                    blockButton()
                } else {
                    isEmptyPhone = true
                    blockButton()
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding!!.textErrorLogin.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyPhone = false
                    blockButton()
                }
            }
        })

        binding!!.editTextPassword.addTextChangedListener(object : TextWatcher {
            // when there is no text added
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyPassword = false
                    blockButton()
                } else {
                    isEmptyPassword = true
                    blockButton()
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding!!.textErrorLogin.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyPassword = false
                    blockButton()
                }
            }
        })
    }

    private fun blockButton(){
        if (isEmptyPhone && isEmptyPassword){
            binding!!.buttonLogin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.black))
            binding!!.buttonLogin.isEnabled = true
        }else if (!isEmptyPhone || !isEmptyPassword){
            binding!!.buttonLogin.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_block_button))
            binding!!.buttonLogin.isEnabled = false
        }
    }

    fun backToOnboarding(){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val onboardingFragment = OnBoardingFragment()
        transaction.replace(R.id.layout_fragment, onboardingFragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun login(login: String, password: String){
        RetrofitHelper().getApi().loginUser(UserLoginEntity(login, password)).enqueue(object :
            Callback<GetTokenResponse> {
            override fun onResponse(
                call: Call<GetTokenResponse>,
                response: Response<GetTokenResponse>
            ) {
                if (response.isSuccessful) {
//                    tokenManager.saveToken(response.body()!!.token.toString())
//                    firstEntryManager.saveFirstEntry(true)
                    val transaction = activity!!.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.layout_fragment, BottomNavBarFragment())
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }else{
                    binding!!.textErrorLogin.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}