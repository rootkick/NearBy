package model.Venue

import com.google.gson.annotations.SerializedName
import model.Venue.Reasons
import model.Venue.Venue

data class VenueItemModel (
    @SerializedName("reasons") val reasons : Reasons,
    @SerializedName("venue") val venue : Venue
)