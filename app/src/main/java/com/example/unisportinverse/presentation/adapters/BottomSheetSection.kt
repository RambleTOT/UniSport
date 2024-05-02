package com.example.unisportinverse.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.R
import com.example.unisportinverse.data.model.GetGroundsResponse
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.databinding.BottomSheetGroundBinding
import com.example.unisportinverse.databinding.BottomSheetSectionBinding
import com.example.unisportinverse.databinding.FragmentMapBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSection(val i: GetSectionsResponse) : BottomSheetDialogFragment() {

    private var binding: BottomSheetSectionBinding? = null

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetSectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.nameSection.text = i.name
        binding!!.place.text = i.address
        binding!!.ageSection.text = "${i.age} лет"
        binding!!.timeSection.text = i.days
        binding!!.ratingSection.text = i.rating
        binding!!.descriptionSection.text = i.description
//        binding!!.buttonCloseGround.setOnClickListener {
//
//        }

    }

    companion object {
        const val TAG = "AddBottomSheetGround"
    }

}