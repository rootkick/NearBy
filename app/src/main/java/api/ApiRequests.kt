package api

import model.Venue.ExploreModel
import android.content.Context
import model.photo.PhotoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ihsib on 8/19/2020.
 */

class ApiRequests {

    fun getExplore(
        context: Context,
        latLong : String,
        explorerDelegate: ApiDelegate.ExploreDelegate
    ) {
        val apiHelper: ApiFunction = ApiHelper.getRetrofit()!!.create(ApiFunction::class.java)

        val fDate: String = SimpleDateFormat("yyyyMMdd").format(Date())

        apiHelper.getExplore(latLong, 1000, ApiHelper.CLIENT_ID, ApiHelper.CLIENT_SECRET, fDate)
            .enqueue(object : Callback<ExploreModel> {
                override fun onResponse(
                    call: Call<ExploreModel>,
                    response: Response<ExploreModel>
                ) {
                    if (response.isSuccessful) {
                        explorerDelegate.onRequestCompleted(true, response.body(), "")
                    } else {
                        explorerDelegate.onRequestCompleted(false, null, response.message())
                    }
                }

                override fun onFailure(call: Call<ExploreModel>, t: Throwable) {
                    explorerDelegate.onRequestCompleted(false, null, t.message.toString())
                }
            })
    }


    fun getPhoto(
        context: Context,
        ventureId : String,
        photoDelegate: ApiDelegate.PhotoDelegate
    ) {
        val apiHelper: ApiFunction = ApiHelper.getRetrofit()!!.create(ApiFunction::class.java)

        val fDate: String = SimpleDateFormat("yyyyMMdd").format(Date())

        apiHelper.getPhoto(ventureId, ApiHelper.CLIENT_ID, ApiHelper.CLIENT_SECRET, fDate)
            .enqueue(object : Callback<PhotoModel> {
                override fun onResponse(
                    call: Call<PhotoModel>,
                    response: Response<PhotoModel>
                ) {
                    if (response.isSuccessful) {
                        photoDelegate.onRequestCompleted(true, response.body(), "")
                    } else {
                        photoDelegate.onRequestCompleted(false, null, response.message())
                    }
                }

                override fun onFailure(call: Call<PhotoModel>, t: Throwable) {
                    photoDelegate.onRequestCompleted(false, null, t.message.toString())
                }
            })
    }

}