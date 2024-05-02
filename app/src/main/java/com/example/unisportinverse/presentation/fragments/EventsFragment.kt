package com.example.unisportinverse.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unisportinverse.data.model.GetEventsResponse
import com.example.unisportinverse.data.model.GetRecommendResponse
import com.example.unisportinverse.databinding.FragmentEventsBinding
import com.example.unisportinverse.presentation.adapters.EventsAdapter
import com.example.unisportinverse.presentation.adapters.RecommendationsAdapter
import com.example.unisportinverse.presentation.adapters.SectionsAdapter
import com.example.unisportinverse.presentation.managers.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsFragment : Fragment() {

    private var binding: FragmentEventsBinding? = null

    private lateinit var recommendList: List<GetRecommendResponse>
    private lateinit var adapterRecommend: RecommendationsAdapter

    private lateinit var eventsList: List<GetEventsResponse>
    private lateinit var adapterEvents: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getRecommendations()
        getEvents()
    }

    private fun init(){
        recommendList = listOf()
    }


    private fun getRecommendations(){
        RetrofitHelper().getApi().getRecommendations().enqueue(object :
            Callback<List<GetRecommendResponse>> {

            override fun onResponse(
                call: Call<List<GetRecommendResponse>>,
                response: Response<List<GetRecommendResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    recommendList = response.body()!!
                    binding!!.recyclerViewSections.apply {
                        adapterRecommend = RecommendationsAdapter(recommendList)
                        adapter = adapterRecommend
                        layoutManager =
                            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);

                    }

                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetRecommendResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getEvents(){
        RetrofitHelper().getApi().getEvents().enqueue(object :
            Callback<List<GetEventsResponse>> {

            override fun onResponse(
                call: Call<List<GetEventsResponse>>,
                response: Response<List<GetEventsResponse>>
            ) {
                if (response.isSuccessful){
                    Log.d("MyLog", response.body().toString())
                    eventsList = response.body()!!
                    binding!!.recyclerViewEvents.apply {
                        adapterEvents = EventsAdapter(eventsList)
                        adapter = adapterEvents
                        layoutManager = LinearLayoutManager(requireActivity())

                    }

                }
                Log.d("MyLog", response.toString())
            }

            override fun onFailure(call: Call<List<GetEventsResponse>>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
                Toast.makeText(activity, "Возникла ошибка, проверьте подключение", Toast.LENGTH_SHORT).show()
            }

        })
    }

}