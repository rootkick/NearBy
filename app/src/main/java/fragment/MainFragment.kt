package fragment

import activity.MainActivity
import adapter.VenuesAdapter
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import api.ApiDelegate
import api.ApiRequests
import com.ihsib.nearby.R
import kotlinx.android.synthetic.main.fragment_main.*
import model.Venue.ExploreModel

class MainFragment : Fragment(), ApiDelegate.ExploreDelegate {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    private lateinit var location: Location
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getVenues()
    }

    fun triggerRefresh(location: Location) {
        setLocation(location)
        getVenues()
    }

    fun setLocation(location: Location) {
        this.location = location;
    }

    private fun getVenues() {
        (activity as MainActivity).showLoading(true)
        (activity as MainActivity).showError(false)
        (activity as MainActivity).showNodata(false)

        val latLong: String = location.latitude.toString() + "," + location.latitude

        if (context != null) {
            rv_venues.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            ApiRequests().getExplore(context!!, latLong, this)
        }
    }

    private var venuesAdapter: VenuesAdapter? = null
    override fun onRequestCompleted(status: Boolean, explorerModel: ExploreModel?, error: String) {
        if (status && context != null) {
            val venuesList = explorerModel?.response?.groups?.get(0)?.items
            if (venuesList?.size == 0) {
                venuesAdapter?.clear()
                (activity as MainActivity).showNodata(true)
            } else {
                venuesAdapter = VenuesAdapter(context!!, venuesList!!)
                rv_venues.adapter = venuesAdapter
            }

        } else {
            (activity as MainActivity).showError(true)
        }
        (activity as MainActivity).showLoading(false)
    }

}