package `interface`

import android.location.Location

/**
 * Created by Ihsib on 8/20/2020.
 */
interface LocationDelegate {
    fun onLocationIdentified(location: Location)
    fun onLocationUpdated(location: Location)
}