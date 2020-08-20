package utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Ihsib on 8/19/2020.
 */

/**
 * Check if device is connected to a network
 *
 * @return
 */

class Helpers {

    fun isNetworkConnected(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}