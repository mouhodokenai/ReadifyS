package com.example.readify

import android.content.Context
import android.content.SharedPreferences

object SharedPreferences {

    private const val PREFS_NAME = "MyPrefs"
    private const val KEY_USERID = "userid"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserId(id: Int) {
        sharedPreferences.edit().putInt(KEY_USERID, id).apply()
    }

    fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_USERID, 0)
    }

    fun containsKey(): Boolean {
        return sharedPreferences.contains(KEY_USERID)
    }

    fun setData(keyword : String, data : String){
        sharedPreferences.edit().putString(keyword, data).apply()
    }

    fun getData(keyword: String) : String?{
        return sharedPreferences.getString(keyword, "user")
    }

    fun clearData() {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }
}
