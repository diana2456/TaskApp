package space.lobanovi.taskapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.lang.reflect.Array.set
import java.nio.file.Paths.get


class Preferences(private val context: Context) {
    val sharedPref: SharedPreferences = context.getSharedPreferences("prefences",MODE_PRIVATE)


    fun isBoardingShowed():Boolean{
        return sharedPref.getBoolean("board",false)
    }

    fun setBoardingShowed(isSnow:Boolean){
        sharedPref.edit().putBoolean("board",isSnow).apply()
    }
    fun setProfile(isSnow: String) {
        sharedPref.edit().putString("image",isSnow).apply()
    }

    fun isProfile(image:String){
      sharedPref.getString("image",image)
    }


}





