package jp.icecreamparfait.intern.cyberagent.holidayin.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.listeners.FoursquareVenuesRequestListener;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.AsyncCallback;
import jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab1Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab2Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.MyTabListener;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.Searcher;
import jp.icecreamparfait.intern.cyberagent.holidayin.VenueAdapter;

import static jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab1Fragment.OnFragmentInteractionListener;

public class DetailActivity extends Activity implements
        Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener, LocationListener {

    private double mLatitude;
    private double mLongitude;

    private Fragment fragment_tab1;

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
        actionBar.setTitle("検索結果");
        ActionBar.Tab tab1 = actionBar.newTab().setText("スポット");
        ActionBar.Tab tab2 = actionBar.newTab().setText("プラン");

        fragment_tab1 = new Tab1Fragment();
        Fragment fragment_tab2 = new Tab2Fragment();

//        Bundle data = new Bundle();
//        data.putString("query", getIntent().getExtras().getString("query"));
//        fragment_tab1.setArguments(data);

        tab1.setTabListener(new MyTabListener(fragment_tab1));
        tab2.setTabListener(new MyTabListener(fragment_tab2));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        EasyFoursquareAsync api = new EasyFoursquareAsync(this);
        Location loc = new Location("");
        loc.setLongitude(139.7069874);
        loc.setLatitude(35.6432274);

        VenuesCriteria vCriteria = new VenuesCriteria();
        vCriteria.setQuantity(10);
        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        vCriteria.setQuery("shibuya");
        vCriteria.setLocation(loc);
        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
                ResultStore.get().setResult(venues);
                fragment_tab1 = new Tab1Fragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("finished", true);
                fragment_tab1.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment_tab1);
                ft.commit();
                //fragment_tab1.onCreateView(getLayoutInflater(), (ViewGroup)findViewById(R.id.container), bundle);

            }

            @Override
            public void onError(String errorMsg) {

            }
        }, vCriteria);

//        Searcher searcher = new Searcher(this, new AsyncCallback() {
//            @Override
//            public void onPreExecute() {
//
//            }
//
//            @Override
//            public void onPostExecute(List<Venue> result) {
//                ResultStore.get().setResult(result);
//            }
//
//            @Override
//            public void onProgressUpdate(int progress) {
//
//            }
//
//            @Override
//            public void onCancelled() {
//
//            }
//        });
//        searcher.execute(ResultStore.get().getQuery());

//        List<Venue> venues = null;
//        try {
//            venues = searcher.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        ResultStore.get().setResult(venues);


        setActionBar();
        setLocationManager();
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



