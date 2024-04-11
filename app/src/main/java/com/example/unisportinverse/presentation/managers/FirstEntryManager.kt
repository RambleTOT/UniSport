package com.example.unisportinverse.presentation.managers

import android.content.Context

class FirstEntryManager(context: Context) {

    companion object{
        private const val PREF_FIRST_ENTRY = "PREF_FIRST_ENTRY"
        private const val FIRST_ENTRY = "FIRST_ENTRY"
    }

    private var sPref = context.getSharedPreferences(PREF_FIRST_ENTRY, Context.MODE_PRIVATE)

    fun saveFirstEntry(entry: Boolean){
        val editor = sPref.edit()
        editor.putBoolean(FIRST_ENTRY, entry)
        editor.apply()
    }

    fun getFirstEntry() : Boolean? {
        return sPref.getBoolean(FIRST_ENTRY, false)
    }

}