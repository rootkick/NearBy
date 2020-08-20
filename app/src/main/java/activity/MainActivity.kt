package activity

import `interface`.LocationDelegate
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ihsib.nearby.R
import controller.LocationController
import fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import utils.AppPreferences
import utils.OperationalMode


/**
 * Created by Ihsib on 8/19/2020.
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainActivity : AppCompatActivity(), LocationDelegate, PopupMenu.OnMenuItemClickListener {

    private lateinit var locationController: LocationController
    private lateinit var mainFragment: MainFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val mode = AppPreferences.loadOperationalMode();

        tv_appMode.text = mode.operationalMode

        tv_appMode.setOnClickListener {
            showPopup(it)
        }

        locationController = LocationController(this, this)
        locationController.initLocationController(mode.operationalMode == OperationalMode.REAL_TIME.operationalMode)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.realtime -> {
                AppPreferences.storeOperationalMode(OperationalMode.REAL_TIME)
                locationController.initLocationController(true)
                tv_appMode.text = resources.getString(R.string.realtime)
                return true
            }
            R.id.single_update -> {
                AppPreferences.storeOperationalMode(OperationalMode.SINGLE_UPDATE)
                locationController.removeLocationListener()
                tv_appMode.text = resources.getString(R.string.single_update)
                return true
            }
        }
        return false
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.modes_menu)
        popup.show()
    }


    fun showLoading(show: Boolean) {
        loadingLayout.visibility = if (show) VISIBLE else GONE
    }

    fun showError(show: Boolean) {
        errorLayout.visibility = if (show) VISIBLE else GONE
    }

    fun showNodata(show: Boolean) {
        noItemsLayout.visibility = if (show) VISIBLE else GONE
    }

    override fun onLocationIdentified(location: Location) {
        mainFragment = MainFragment()
        mainFragment.setLocation(location)

        showLoading(false)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, mainFragment, "MainFragment").commit()
    }

    override fun onLocationUpdated(location: Location) {
        mainFragment.triggerRefresh(location)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {

                        locationController.initLocationController(AppPreferences.loadOperationalMode().operationalMode == OperationalMode.REAL_TIME.operationalMode)
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }


}
