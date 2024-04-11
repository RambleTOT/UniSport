package com.example.unisportinverse.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unisportinverse.R
import com.example.unisportinverse.databinding.BottomSheetGroundBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetGround : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BottomSheetGroundBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "AddBottomSheetGround"
    }

}