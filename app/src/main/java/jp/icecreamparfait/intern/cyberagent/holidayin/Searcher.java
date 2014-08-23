package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.DetailVenue;
import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/22.
 */
public class Searcher extends AsyncTask<String, Void, List<Venue> >{

    private Activity mActivity;
    private AsyncCallback mCallback;

    public Searcher(Activity activity, AsyncCallback callback) {
        mActivity = activity;
        mCallback = callback;
    }

    @Override
    protected List<Venue> doInBackground(String... strings) {
        String query = strings[0];

        //EasyFoursquare api = new EasyFoursquare(mActivity);
        EasyFoursquare api = new EasyFoursquare();

        Location loc = new Location("");
        loc.setLongitude(139.7069874);
        loc.setLatitude(35.6432274);

        VenuesCriteria vCriteria = new VenuesCriteria();
        vCriteria.setQuantity(10);
        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        vCriteria.setQuery(query);
        vCriteria.setLocation(loc);

        List<Venue> venues = api.getVenuesNearby(vCriteria);

        return venues;
    }

    @Override
    protected void onPostExecute(List<Venue> venues) {
        super.onPostExecute(venues);
        mCallback.onPostExecute(venues);
    }
}
