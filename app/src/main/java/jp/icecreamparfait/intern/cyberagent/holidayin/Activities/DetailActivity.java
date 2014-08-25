package jp.icecreamparfait.intern.cyberagent.holidayin.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.listeners.FoursquareVenuesRequestListener;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.FragmentStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab1Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.Fragments.Tab2Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plans.BasePlan;
import jp.icecreamparfait.intern.cyberagent.holidayin.MyTabListener;
import jp.icecreamparfait.intern.cyberagent.holidayin.QueryStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;

public class DetailActivity extends Activity implements
        Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener,
        FoursquareVenuesRequestListener{

    private EasyFoursquareAsync api;

    private void setActionBar() {
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("検索結果");
        ActionBar.Tab tab1 = actionBar.newTab().setText("スポット");
        ActionBar.Tab tab2 = actionBar.newTab().setText("プラン");

        tab1.setTag("tab1");
        tab2.setTag("tab2");

        Fragment fragment_tab1 = new Tab1Fragment();
        Fragment fragment_tab2 = new Tab2Fragment();

        FragmentStore.setTab1Fragment(fragment_tab1);
        FragmentStore.setTab2Fragment(fragment_tab2);

        tab1.setTabListener(new MyTabListener());
        tab2.setTabListener(new MyTabListener());

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
        if (LocationStore.getLocation() == null) {
            Toast.makeText(this, "位置取得ができませんでした。", Toast.LENGTH_SHORT);
        }
        vCriteria.setLocation(LocationStore.getLocation());
        vCriteria.setRadius((QueryStore.getMovingTime().ordinal()+1) * 5000);
        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);

        api.getVenuesNearby(this, vCriteria);
    }

    private void startSearchWithPlan() {
        int planId = QueryStore.getPlanMood().ordinal();
        final BasePlan basePlan = BasePlan.Plans[planId];

        Log.d("icecream", basePlan.toString());

        basePlan.resetPlan();
//        GracefulPlan.resetPlan();

        String query = ResultStore.getQuery();

        VenuesCriteria criteria = new VenuesCriteria();
        criteria.setQuantity(20);
        criteria.setQuery(query);
        criteria.setLocation(LocationStore.getLocation());
        criteria.setRadius((QueryStore.getMovingTime().ordinal()+1) * 5000);
        criteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
//        criteria.setCategories(GracefulPlan.belongingCategories.keySet());
        criteria.setCategories(basePlan.belongingCategories.keySet());
        Log.d("icecream", basePlan.belongingCategories.keySet().toString());


        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
//                GracefulPlan.setMainSpots(venues);
                basePlan.setMainSpots(venues);
//                Plan plan = GracefulPlan.makePlan();
                Plan plan = basePlan.makePlan();
                if (plan != null) {
                    ResultStore.setPlan(plan);
                }
            }

            @Override
            public void onError(String errorMsg) {
                Log.d("icecream", errorMsg);
            }
        }, criteria);

        criteria = new VenuesCriteria();
        criteria.setQuantity(10);
        criteria.setQuery(query);
        criteria.setLocation(LocationStore.getLocation());
        criteria.setRadius((QueryStore.getMovingTime().ordinal()+1) * 5000);
        criteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
//        criteria.setCategories(GracefulPlan.attachments.keySet());
        criteria.setCategories(basePlan.attachments.keySet());

        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
//                GracefulPlan.setAttachments(venues);
                basePlan.setAttachments(venues);
//                Plan plan = GracefulPlan.makePlan();
                Plan plan = basePlan.makePlan();
                if (plan != null) {
                    ResultStore.setPlan(plan);
                }
            }

            @Override
            public void onError(String errorMsg) { Log.d("icecream", errorMsg); }
        }, criteria);
    }

    @Override
    public void onVenuesFetched(ArrayList<Venue> venues) {
        ResultStore.setResult(venues);

        Bundle bundle = new Bundle();
        bundle.putBoolean("finished", true);
        Fragment fragment_tab1 = new Tab1Fragment();
        fragment_tab1.setArguments(bundle);

        FragmentStore.setTab1Fragment(fragment_tab1);

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
        startSearch(ResultStore.getQuery());
        startSearchWithPlan();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.detail, menu);
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



