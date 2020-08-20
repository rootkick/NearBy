package activity

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import utils.AppPreferences

/**
 * Created by Ihsib on 8/20/2020.
 */
class NearBy : Application() {
    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this);
        AppPreferences.init(applicationContext)
    }
}