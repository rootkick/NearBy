package model.Venue

import com.google.gson.annotations.SerializedName

data class Warning (
	@SerializedName("text") val text : String
)