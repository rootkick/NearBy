package model.Venue

import com.google.gson.annotations.SerializedName

data class SuggestedBounds (

    @SerializedName("ne") val ne : Ne,
    @SerializedName("sw") val sw : Sw
)