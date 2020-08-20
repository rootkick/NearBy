package model.Venue

import com.google.gson.annotations.SerializedName

data class Reasons (
	@SerializedName("count") val count : Int,
	@SerializedName("items") val items : List<VenueItemModel>
)