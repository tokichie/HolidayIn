package jp.icecreamparfait.intern.cyberagent.holidayin.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
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
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.FragmentStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.fragments.Tab1Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.fragments.Tab2Fragment;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.models.plans.BasePlan;
import jp.icecreamparfait.intern.cyberagent.holidayin.MyTabListener;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.QueryStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.ResultStore;

public class DetailActivity extends Activity implements
        Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener{

    private EasyFoursquareAsync api;

    private void setActionBar() {
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

        int planId = QueryStore.getPlanMood().ordinal();
        final BasePlan basePlan = BasePlan.Plans[planId];

        VenuesCriteria criteria = new VenuesCriteria();
        criteria.setQuantity(10);
        criteria.setQuery(query);

        criteria.setLocation(LocationStore.getLocation());
        criteria.setRadius((QueryStore.getMovingTime().ordinal()+1) * 5000);
        criteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        criteria.setCategories(basePlan.belongingCategories.keySet());

        final Context context = this;
        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
                if (venues.size() == 0) {
                    Toast toast = Toast.makeText(context, "見つかりませんでした", Toast.LENGTH_SHORT);
                    toast.show();
                    ResultStore.setIsFound(false);
                    return;
                }

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
        }, criteria);
    }

    private void startSearchWithPlan() {
        int planId = QueryStore.getPlanMood().ordinal();
        final BasePlan basePlan = BasePlan.Plans[planId];

        basePlan.resetPlan();

        String query = QueryStore.getQuery();

        VenuesCriteria criteria = new VenuesCriteria();
        criteria.setQuantity(20);
        criteria.setQuery(query);
        criteria.setLocation(LocationStore.getLocation());
        criteria.setRadius((QueryStore.getMovingTime().ordinal()+1) * 5000);
        criteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        criteria.setCategories(basePlan.belongingCategories.keySet());

        final Context context = this;
        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
                if (venues.size() == 0) {
                    ResultStore.setIsFound(false);
                    return;
                }
                basePlan.setMainSpots(venues);
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
        criteria.setCategories(basePlan.attachments.keySet());

        api.getVenuesNearby(new FoursquareVenuesRequestListener() {
            @Override
            public void onVenuesFetched(ArrayList<Venue> venues) {
                if (venues.size() == 0) {
                    ResultStore.setIsFound(false);
                    return;
                }
                basePlan.setAttachments(venues);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ResultStore.setIsFound(true);

        api = new EasyFoursquareAsync(this);

        setActionBar();

        startSearch(QueryStore.getQuery());
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



