package model.photo

import com.google.gson.annotations.SerializedName

/**
 * Created by Ihsib on 8/19/2020.
 */

data class PhotosModel (
    @SerializedName("count") val count : Int,
    @SerializedName("items") val items : ArrayList<PhotoItemModel>,
    @SerializedName("dupesRemoved") val suffix : Int
)