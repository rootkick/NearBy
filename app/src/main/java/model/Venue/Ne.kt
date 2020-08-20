package model.Venue

import com.google.gson.annotations.SerializedName

data class Ne (
	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)