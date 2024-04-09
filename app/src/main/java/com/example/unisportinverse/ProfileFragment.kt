package com.example.unisportinverse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.unisportinverse.data.model.GetMyAccount
import com.example.unisportinverse.databinding.FragmentProfileBinding
import com.example.unisportinverse.databinding.FragmentRegistrationBinding
import com.example.unisportinverse.presentation.managers.RetrofitHelper
import com.example.unisportinverse.presentation.managers.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getMyAccount()
    }

    private fun init(){
        tokenManager = TokenManager(requireActivity())
    }

    private fun getMyAccount(){
        RetrofitHelper().getApi().getMyAccount("Token ${tokenManager.getToken()}").enqueue(object :
            Callback<GetMyAccount> {
            override fun onResponse(
                call: Call<GetMyAccount>,
                response: Response<GetMyAccount>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    binding!!.nameProfile.text = "${body!!.firstname} ${body.surname}"
                    binding!!.phoneProfile.text = body.phone
                }
            }

            override fun onFailure(call: Call<GetMyAccount>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}