package model.Venue

import com.google.gson.annotations.SerializedName

data class GroupModel (
	@SerializedName("type") val type : String,
	@SerializedName("name") val name : String,
	@SerializedName("items") val items : ArrayList<VenueItemModel>
)