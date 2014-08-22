package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Venue;
import android.view.Menu;
import android.view.MenuItem;


public class DetailActivity extends Activity implements Tab1Fragment.OnFragmentInteractionListener, LocationListener {

    private List<Venue> venueList;
    private double mLatitude;
    private double mLongitude;

    public void setVenueList(List<Venue> venues) {
        venueList = venues;
    }

    private void setVenues(List<Venue> venues) {
        VenueAdapter adapter = new VenueAdapter(this, 0, venues);

        ListView listView = (ListView) findViewById(R.id.listView_detail);
        listView.setAdapter(adapter);
    }

    private void setLocationManager() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(provider, 0L, 0f, this);
    }

    private void setActionBar() {
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab()
                .setText("ページ１")
                .setTabListener(new TabListener<Tab1Fragment>(
                        this, "tag1", Tab1Fragment.class)));
        actionBar.addTab(actionBar.newTab()
                .setText("ページ２")
                .setTabListener(new TabListener<Tab2Fragment>(
                        this, "tag2", Tab2Fragment.class)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setActionBar();
        setLocationManager();

        EasyFoursquare efs = new EasyFoursquare(DetailActivity.this);


        Location loc = new Location("");
        loc.setLongitude(139.7069874);
        loc.setLatitude(35.6432274);

        VenuesCriteria vCriteria = new VenuesCriteria();
        vCriteria.setQuantity(10);
        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        vCriteria.setQuery(getIntent().getExtras().getString("query"));
        vCriteria.setLocation(loc);

        List<Venue> venues = efs.getVenuesNearby(vCriteria);

        /*
        List<Integer> counts = new ArrayList<Integer>();
        for (Venue venue: venues) {
            PhotosGroup pg = efs.getVenuePhotos(venue.getId());
            counts.add(pg.getCount());
        }
        Log.d("icecream", counts.toString());
        */

        /*
        Venue aVenue = venues.get(1);
        PhotosGroup photos = efs.getVenuePhotos(aVenue.getId());
        if (photos.getCount() > 0) {
            List<PhotoItem> items = photos.getItems();
            PhotoItem item = items.get(0);
            try {
                URL url = new URL(item.getPrefix() + "200x200" +  item.getSuffix());
                Log.d("icecream", url.toString());
                InputStream istream = url.openStream();
                Bitmap bmp = BitmapFactory.decodeStream(istream);
                ImageView view = (ImageView) findViewById(R.id.imageView_detail);
                //view.setImageBitmap(bmp);
                istream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

        /*
        List<PhotoItem> photos = new ArrayList<PhotosItem>();
        for (Venue venue: venues) {
            PhotosGroup photo = efs.getVenuePhotos(venue.getId());
            List<PhotoItem> photoItems = photo.getItems();
            PhotoItem item = photoItems.get(0);
            item.
            photos.add(photo);
        }*/
        Log.d("Icecream", venues.toString());

        setVenues(venues);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
