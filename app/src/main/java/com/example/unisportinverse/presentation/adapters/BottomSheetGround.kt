package com.example.unisportinverse.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.R
import com.example.unisportinverse.data.model.GetGroundsResponse
import com.example.unisportinverse.databinding.BottomSheetGroundBinding
import com.example.unisportinverse.databinding.FragmentMapBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetGround(val i: GetGroundsResponse) : BottomSheetDialogFragment() {

    private var binding: BottomSheetGroundBinding? = null

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetGroundBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog", i.name.toString())
        binding!!.nameGround.text = i.name
        binding!!.infoGround.text = i.description
        binding!!.placeGround.text = i.address
        binding!!.timeGround.text = i.timetable
//        binding!!.buttonCloseGround.setOnClickListener {
//
//        }
    }

    companion object {
        const val TAG = "AddBottomSheetGround"
    }

}