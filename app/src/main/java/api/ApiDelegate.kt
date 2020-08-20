package api

import model.Venue.ExploreModel
import model.photo.PhotoModel

/**
 * Created by Ihsib on 8/19/2020.
 */
interface ApiDelegate {

    interface ExploreDelegate {
        fun onRequestCompleted(status: Boolean, explorerModel: ExploreModel?, error : String)
    }

    interface PhotoDelegate {
        fun onRequestCompleted(status: Boolean, photoModel: PhotoModel?, error : String)
    }
}