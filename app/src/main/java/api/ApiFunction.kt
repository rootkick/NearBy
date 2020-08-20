package api

import model.Venue.ExploreModel
import model.photo.PhotoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ihsib on 8/19/2020.
 */

interface ApiFunction {
    @GET("explore")
    fun getExplore(
        @Query("ll") latlong: String,
        @Query("radius") radius: Int,
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("v") dateFormat: String
    ): Call<ExploreModel>


    @GET("{venture_id}/photos")
    fun getPhoto(
        @Path("venture_id") venture_id: String,
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("v") dateFormat: String
    ): Call<PhotoModel>

}
