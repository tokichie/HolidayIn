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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.listeners.FoursquareVenuesRequestListener;
import br.com.condesales.listeners.VenuePhotosListener;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.AsyncCallback;
import jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab1Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab2Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plans.IntellectualPlan;
import jp.icecreamparfait.intern.cyberagent.holidayin.MyTabListener;
import jp.icecreamparfait.intern.cyberagent.holidayin.PhotoStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.Searcher;
import jp.icecreamparfait.intern.cyberagent.holidayin.VenueAdapter;

import static jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab1Fragment.OnFragmentInteractionListener;

public class DetailActivity extends Activity implements
        Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener,
        FoursquareVenuesRequestListener{

    private Fragment fragment_tab1;
    private EasyFoursquareAsync api;

    private void setActionBar() {
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("検索結果");
        ActionBar.Tab tab1 = actionBar.newTab().setText("スポット");
        ActionBar.Tab tab2 = actionBar.newTab().setText("プラン");

        fragment_tab1 = new Tab1Fragment();
        Fragment fragment_tab2 = new Tab2Fragment();

        tab1.setTabListener(new MyTabListener(fragment_tab1));
        tab2.setTabListener(new MyTabListener(fragment_tab2));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
    }

    private void startSearch(String query) {
//        Location loc = new Location("");
//        loc.setLongitude(139.7069874);
//        loc.setLatitude(35.6432274);

        VenuesCriteria vCriteria = new VenuesCriteria();
        vCriteria.setQuantity(10);
        vCriteria.setQuery(query);
        vCriteria.setLocation(LocationStore.getLocation());
        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);

        api.getVenuesNearby(this, vCriteria);
    }

    private void startSearchWithPlan() {
        String query = "";

        VenuesCriteria criteria = new VenuesCriteria();
        criteria.setQuantity(20);
        criteria.setLocation(LocationStore.getLocation());
        criteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        criteria.setCategories(IntellectualPlan.belongingCategories.keySet());

        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
                IntellectualPlan.makePlan(venues, 120);
                Log.d("icecream", venues.toString());
            }

            @Override
            public void onError(String errorMsg) {
                Log.d("icecream", errorMsg);
            }
        }, criteria);
        //IntellectualPlan.search(api, criteria);
    }

    private void fetchPhotos(ArrayList<Venue> venues) {
        for (final Venue venue: venues) {
            api.getVenuePhotos(venue.getId(), new VenuePhotosListener() {
                @Override
                public void onGotVenuePhotos(PhotosGroup photosGroup) {
//                    PhotoStore.get().putPhotosGroup(venue.getId(), photosGroup);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
    }

    @Override
    public void onVenuesFetched(ArrayList<Venue> venues) {
//        fetchPhotos(venues);

        ResultStore.get().setResult(venues);

        Bundle bundle = new Bundle();
        bundle.putBoolean("finished", true);
        fragment_tab1 = new Tab1Fragment();
        fragment_tab1.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment_tab1);
        ft.commit();
    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        api = new EasyFoursquareAsync(this);

        setActionBar();
        startSearch(ResultStore.get().getQuery());
        startSearchWithPlan();
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
    public void onFragmentInteraction(Uri uri) {

    }
}



