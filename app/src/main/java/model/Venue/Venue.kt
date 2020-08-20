package model.Venue

import com.google.gson.annotations.SerializedName

data class Venue (
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("location") val location : Location,
    @SerializedName("categories") val categories : List<CategoryModel>,
    @SerializedName("popularityByGeo") val popularityByGeo : Double,
    @SerializedName("venuePage") val venuePage : VenuePage
)