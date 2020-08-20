package controller

import `interface`.LocationDelegate
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by Ihsib on 8/20/2020.
 */

class LocationController(
    private val activity: Activity,
    private val locationDelegate: LocationDelegate
) : LocationListener {

    private var locationManager: LocationManager? = null
    private var isRealtime = true
    fun initLocationController(isRealtime: Boolean) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !==
            PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
            return
        }
        this.isRealtime = isRealtime

        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000L, 10f, this)
    }

    fun removeLocationListener() {
        locationManager?.removeUpdates(this)
    }

    private var lastUpdatedLocation: Location? = null
    override fun onLocationChanged(newLocation: Location?) {
        if (lastUpdatedLocation == null && newLocation != null) { //Init in first time.
            lastUpdatedLocation = newLocation
            locationDelegate.onLocationIdentified(newLocation)
        } else {
            if (newLocation != null && lastUpdatedLocation?.distanceTo(newLocation)!! >= 500) { //Check if distance if 500m to update the list of nearby venues.
                lastUpdatedLocation = newLocation;
                locationDelegate.onLocationUpdated(newLocation)
            }
        }

        if (!isRealtime) {
            removeLocationListener()
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

}