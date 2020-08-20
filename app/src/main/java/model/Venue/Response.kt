package model.Venue

import com.google.gson.annotations.SerializedName
import model.photo.PhotosModel


data class Response(

    @SerializedName("warning") val warning: Warning,
    @SerializedName("suggestedRadius") val suggestedRadius: Int,
    @SerializedName("headerLocation") val headerLocation: String,
    @SerializedName("headerFullLocation") val headerFullLocation: String,
    @SerializedName("headerLocationGranularity") val headerLocationGranularity: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("suggestedBounds") val suggestedBounds: SuggestedBounds,
    @SerializedName("groups") val groups: ArrayList<GroupModel>,
    @SerializedName("photos") val photos: PhotosModel
)