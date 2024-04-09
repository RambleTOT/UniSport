package com.example.unisportinverse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.databinding.FragmentMapBinding
import com.example.unisportinverse.databinding.FragmentSectionBinding
import com.example.unisportinverse.presentation.adapters.SectionsAdapter
import com.example.unisportinverse.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SectionFragment : Fragment() {

    private var binding: FragmentSectionBinding? = null
    private lateinit var newsAdapter: SectionsAdapter
    private lateinit var sectionsList: List<GetSectionsResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getSections()
    }

    private fun init(){
        sectionsList = listOf()
    }

    private fun getSections(){
        RetrofitHelper().getApi().getNews().enqueue(object :
            Callback<List<GetSectionsResponse>> {

            override fun onResponse(
                call: Call<List<GetSectionsResponse>>,
                response: Response<List<GetSectionsResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    sectionsList = response.body()!!
                    binding!!.recyclerViewSections.apply {
                        newsAdapter = SectionsAdapter(sectionsList)
                        adapter = newsAdapter
                        layoutManager = LinearLayoutManager(requireActivity());

                    }
                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetSectionsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}