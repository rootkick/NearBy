package utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.Exception

/**
 * Created by Ihsib on 8/20/2020.
 */
class AppPreferences {
    companion object {
        private val PREFERENCES = "PREFERENCES"
        private var prefs: SharedPreferences? = null

        fun init(context: Context) {
            prefs = context.getSharedPreferences(
                PREFERENCES,
                Context.MODE_PRIVATE
            )
        }


        fun storeOperationalMode(operationalMode: OperationalMode) {
            val serializedString = Gson().toJson(operationalMode);
            val editor = prefs!!.edit()
            editor.putString("OperationalMode", serializedString)
            editor.apply()
        }

        fun loadOperationalMode(): OperationalMode {
            return try{
                Gson().fromJson(
                    prefs!!.getString("OperationalMode", null),
                    OperationalMode::class.java
                )
            }catch (e: Exception){
                OperationalMode.REAL_TIME;
            }

        }


    }


}