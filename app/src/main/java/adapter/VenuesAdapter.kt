package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import api.ApiDelegate
import api.ApiRequests
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.textview.MaterialTextView
import com.ihsib.nearby.R
import model.Venue.VenueItemModel
import model.photo.PhotoModel
import java.util.*

/**
 * Created by Ihsib on 8/20/2020.
 */

class VenuesAdapter(
    private val context: Context,
    private val venuesList: ArrayList<VenueItemModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_venue, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return venuesList.size
    }


    fun clear() {
        venuesList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        rawHolder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val holder = rawHolder as ViewHolder
        val venue = venuesList[position]


        //Todo uncomment this lines of code to stay under limit api request.

        ApiRequests().getPhoto(context, venue.venue.id, object : ApiDelegate.PhotoDelegate {
            override fun onRequestCompleted(
                status: Boolean,
                photoModel: PhotoModel?,
                error: String
            ) {
                if(status){
                    if(photoModel?.response?.photos?.items?.size!! > 0){
                        val item  = photoModel.response.photos.items.get(0);
                        val imageUrl = item.prefix + "100x70" + item.suffix
                        holder.iv_venue.setImageURI(imageUrl)
                    }
                }
            }
        })

        holder.tv_venue_name.text = venue.venue.name;
        holder.tv_venue_adress.text =
            venue.venue.location.formattedAddress.toString().replace("[", "").replace("]", "")
    }

    internal class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var iv_venue: SimpleDraweeView = itemView.findViewById(R.id.iv_venue_photo)
        var tv_venue_name: MaterialTextView = itemView.findViewById(R.id.tv_venue_name)
        var tv_venue_adress: MaterialTextView = itemView.findViewById(R.id.tv_venue_adress)

    }
}