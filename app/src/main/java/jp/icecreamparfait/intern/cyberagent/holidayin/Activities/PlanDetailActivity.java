package jp.icecreamparfait.intern.cyberagent.holidayin.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.condesales.models.Location;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.ResultStore;

/**
 * Created by guest on 2014/08/23.
 */
public class PlanDetailActivity extends FragmentActivity {

    private static int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        selectedPosition = getIntent().getExtras().getInt("selectedPosition");

        TextView textView_title = (TextView) findViewById(R.id.textView_title);
        textView_title.setText("プラン" + String.valueOf(selectedPosition+1));
    }




   /* @Override
    protected boolean isRouteDisplayed() {
        return false;
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


    public static class SinglePlanFlagment extends MapFragment {
        private GoogleMap mMap;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View rootView = inflater.inflate(R.layout.fragment_single, container,false);
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            //LatLng latLng = new LatLng(34.9950555, 135.737253);

            setMap();

        }

        private void setMap() {
            Plan plan = ResultStore.getPlan();//PlanStore.getPlans().get(selectedPosition);

            List<Venue> venues = plan.getMainVenues();
            venues.add(plan.getAttachment());

            Log.d("icecream", venues.toString());

            if (mMap == null) {
                mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_plan)).getMap();

            }
            if (mMap != null) {
                //LatLng latLng = null;
                for (Venue venue: venues) {
                    Location loc = venue.getLocation();
                    LatLng latLng = new LatLng(loc.getLat(), loc.getLng());
                    MarkerOptions options = new MarkerOptions();
                    options.position(latLng);
                    options.snippet(venue.getName());
                    mMap.addMarker(options);
                }
//                mMap.moveCamera
//                (CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 10, 0, 0)));
            }
        }

    }
}



