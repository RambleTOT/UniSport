package com.example.unisportinverse.presentation.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.unisportinverse.R
import com.example.unisportinverse.data.model.GetTokenResponse
import com.example.unisportinverse.data.model.UserLoginEntity
import com.example.unisportinverse.data.model.UserRegisterEntity
import com.example.unisportinverse.databinding.FragmentLoginBinding
import com.example.unisportinverse.databinding.FragmentRegistrationBinding
import com.example.unisportinverse.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {

    private var binding: FragmentRegistrationBinding? = null
    private var currentScreen = 0
    private var name = ""
    private var surname = ""
    private var phone = ""
    private var password = ""
    private var role = ""
    private var countChild = ""
    private var clickImage = 0
    private var clickImageCountChild = 0
    private var isEmptyName = false
    private var isEmptySurname = false
    private var isEmptyPhone = false
    private var isEmptyPassword = false
    private var isEmptyCountChild = false

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
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.image_button_click)
            binding!!.buttonBackRegistration.startAnimation(scaleDown)
            backToOnboarding()
        }
        binding!!.buttonRegistrationNext.setOnClickListener{
            if (currentScreen == 2 && binding!!.editTextPassword.text.toString().length < 8){
                binding!!.textErrorRegister.text = getText(R.string.text_error_register_password)
                binding!!.textErrorRegister.visibility = View.VISIBLE
            }else{
                currentScreen++
                plusCurrentScreen()
            }
        }
        binding!!.imageChild.setOnClickListener {
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.click_image_animation)
            binding!!.imageChild.startAnimation(scaleDown)
            binding!!.checkboxImageChild.startAnimation(scaleDown)
            clickImage = 1
            blockButton()
            changeCheckBox()
        }
        binding!!.imageParent.setOnClickListener {
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.click_image_animation)
            binding!!.imageParent.startAnimation(scaleDown)
            binding!!.checkboxImageParent.startAnimation(scaleDown)
            clickImage = 2
            blockButton()
            changeCheckBox()
        }
        binding!!.imageYes.setOnClickListener {
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.click_image_animation)
            binding!!.imageYes.startAnimation(scaleDown)
            binding!!.checkboxImageYes.startAnimation(scaleDown)
            clickImageCountChild = 1
            blockButton()
            changeCheckBoxCount()
        }
        binding!!.imageNo.setOnClickListener {
            val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.click_image_animation)
            binding!!.imageNo.startAnimation(scaleDown)
            binding!!.checkboxImageNo.startAnimation(scaleDown)
            clickImageCountChild = 2
            blockButton()
            changeCheckBoxCount()
        }
        initEditText()
        plusCurrentScreen()
    }

    fun backToOnboarding(){

        when(currentScreen){
            0 -> {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val onboardingFragment = OnBoardingFragment()
                transaction.replace(R.id.layout_fragment, onboardingFragment)
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
            else -> {
                currentScreen--
                plusCurrentScreen()
            }
        }
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

    fun changeCheckBoxCount(){
        when(clickImageCountChild){
            1 -> {
                binding!!.checkboxImageYes.visibility = View.VISIBLE
                binding!!.checkboxImageNo.visibility = View.GONE
            }
            2 -> {
                binding!!.checkboxImageYes.visibility = View.GONE
                binding!!.checkboxImageNo.visibility = View.VISIBLE
            }
        }
    }

    fun plusCurrentScreen(){
        when(currentScreen){
            0 -> {
                binding!!.registrationEditTextPhone.visibility = View.GONE
                binding!!.registrationEditTextName.visibility = View.VISIBLE
                binding!!.registrationEditTextSurname.visibility = View.VISIBLE
                binding!!.editTextName.setText(name)
                binding!!.editTextSurname.setText(surname)
                binding!!.textRegTitle.text = getString(R.string.title_registration_name)
                phone = binding!!.editTextPhone.text.toString()
            }
            1 -> {
                name = binding!!.editTextName.text.toString()
                surname = binding!!.editTextSurname.text.toString()
                binding!!.registrationEditTextName.visibility = View.GONE
                binding!!.registrationEditTextSurname.visibility = View.GONE
                binding!!.registrationEditTextPassword.visibility = View.GONE
                binding!!.registrationEditTextPhone.visibility = View.VISIBLE
                binding!!.editTextPhone.setText(phone)
                binding!!.textRegTitle.text = getString(R.string.title_registration_phone)
                password = binding!!.editTextPassword.text.toString()
            }
            2 -> {
                phone = binding!!.editTextPhone.text.toString()
                binding!!.registrationEditTextPhone.visibility = View.GONE
                binding!!.registrationEditTextPassword.visibility = View.VISIBLE
                binding!!.editTextPassword.setText(password)
                binding!!.textRegTitle.text = getString(R.string.title_registration_password)
                binding!!.linearRole.visibility = View.GONE
                binding!!.registrationEditTextPassword.visibility = View.VISIBLE
            }
            3 -> {
                blockButton()
                password = binding!!.editTextPassword.text.toString()
                binding!!.registrationEditTextPassword.visibility = View.GONE
                binding!!.linearRole.visibility = View.VISIBLE
                binding!!.textRegTitle.text = getString(R.string.title_registration_role)
                binding!!.linearCountChild.visibility = View.GONE
            }
            4 -> {
                if (clickImage == 1){
                    role = "child"
                    //register(name, phone, password, role)
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.layout_fragment, BottomNavBarFragment())
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }else{
                    role = "parent"
                    binding!!.linearRole.visibility = View.GONE
                    binding!!.linearCountChild.visibility = View.VISIBLE
                    binding!!.textRegTitle.text = getString(R.string.title_registration_count_child)
                }
            }
            5->{

            }
        }
    }

    fun initEditText(){
        binding!!.editTextName.addTextChangedListener(object : TextWatcher {
            // when there is no text added
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyName = false
                    blockButton()
                } else {
                    isEmptyName = true
                    blockButton()
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding!!.textErrorRegister.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyName = false
                    blockButton()
                }
            }
        })

        binding!!.editTextSurname.addTextChangedListener(object : TextWatcher {
            // when there is no text added
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    isEmptySurname = false
                    blockButton()
                } else {
                    isEmptySurname = true
                    blockButton()
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding!!.textErrorRegister.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptySurname = false
                    blockButton()
                }
            }
        })

        binding!!.editTextPhone.addTextChangedListener(object : TextWatcher {
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
                binding!!.textErrorRegister.visibility = View.GONE
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
                binding!!.textErrorRegister.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim().isEmpty()) {
                    isEmptyPassword = false
                    blockButton()
                }
            }
        })

        binding!!.editTextName.setOnClickListener {
            binding!!.textErrorRegister.visibility = View.GONE
        }

        binding!!.editTextPhone.setOnClickListener {
            binding!!.textErrorRegister.visibility = View.GONE
        }

        binding!!.editTextPassword.setOnClickListener {
            binding!!.textErrorRegister.visibility = View.GONE
        }
    }

    fun blockButton(){
        if (isEmptyName && isEmptySurname && currentScreen == 0){
            binding!!.buttonRegistrationNext.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.black))
            binding!!.buttonRegistrationNext.isEnabled = true
        }else if (isEmptyPhone && currentScreen == 1){
            binding!!.buttonRegistrationNext.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.black))
            binding!!.buttonRegistrationNext.isEnabled = true
        }else if (isEmptyPassword && currentScreen == 2){
            binding!!.buttonRegistrationNext.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.black))
            binding!!.buttonRegistrationNext.isEnabled = true
        }else if (clickImage > 0 && currentScreen == 3){
            binding!!.buttonRegistrationNext.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.black))
            binding!!.buttonRegistrationNext.isEnabled = true
        }else{
            binding!!.buttonRegistrationNext.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireActivity(), R.color.color_block_button))
            binding!!.buttonRegistrationNext.isEnabled = false
        }
    }

    private fun register(firstname: String, phone: String, password: String, role: String){
        RetrofitHelper().getApi().registerUser(UserRegisterEntity(firstname, phone, password, role)).enqueue(object :
            Callback<GetTokenResponse> {
            override fun onResponse(
                call: Call<GetTokenResponse>,
                response: Response<GetTokenResponse>
            ) {
                if (response.isSuccessful) {
//                    tokenManager.saveToken(response.body()!!.token.toString())
//                    firstEntryManager.saveFirstEntry(true)
//                    val transaction = activity!!.supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.linear_fragment, BottomNavBarFragment())
//                    transaction.disallowAddToBackStack()
//                    transaction.commit()
                    Toast.makeText(activity, "Зарегался", Toast.LENGTH_SHORT).show()
                }else{
                    Log.d("MyLog", response.toString())
                    //binding!!.textErrorLogin.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}