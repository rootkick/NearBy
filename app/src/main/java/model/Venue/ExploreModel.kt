package model.Venue

import com.google.gson.annotations.SerializedName

data class ExploreModel (
	@SerializedName("meta") val meta : Meta,
	@SerializedName("response") val response : Response
)