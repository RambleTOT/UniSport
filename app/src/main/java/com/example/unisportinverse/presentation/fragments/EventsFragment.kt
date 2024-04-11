package com.example.unisportinverse.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.databinding.FragmentEventsBinding
import com.example.unisportinverse.databinding.FragmentSectionBinding

class EventsFragment : Fragment() {

    private var binding: FragmentEventsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

}