package model.photo

import com.google.gson.annotations.SerializedName

/**
 * Created by Ihsib on 8/19/2020.
 */

data class PhotoItemModel(
    @SerializedName("id") val id: String,
    @SerializedName("prefix") val prefix: String,
    @SerializedName("suffix") val suffix: String
)