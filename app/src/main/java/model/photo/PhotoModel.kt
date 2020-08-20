package model.photo

import com.google.gson.annotations.SerializedName
import model.Venue.Meta

/**
 * Created by Ihsib on 8/20/2020.
 */
data class PhotoModel(
    @SerializedName("meta") val meta : Meta,
    @SerializedName("response") val response : PhotosResponse
)