package model.photo

import com.google.gson.annotations.SerializedName

/**
 * Created by Ihsib on 8/20/2020.
 */
data class PhotosResponse(
    @SerializedName("photos") val photos: PhotosModel
)