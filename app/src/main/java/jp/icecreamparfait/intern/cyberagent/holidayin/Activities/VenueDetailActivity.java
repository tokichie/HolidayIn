package jp.icecreamparfait.intern.cyberagent.holidayin.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.xml.transform.Result;

import br.com.condesales.models.Location;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.VenueDetailSetter;

/**
 * Created by guest on 2014/08/23.
 */
public class VenueDetailActivity extends FragmentActivity {

    private static int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);

        selectedPosition = getIntent().getExtras().getInt("selectedPosition");

        View v = findViewById(R.id.linearLayout_venueDetail);
        VenueDetailSetter.set(this, ResultStore.getResult().get(selectedPosition), v);
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


    public static class SingleFlagment extends MapFragment {
        private GoogleMap mMap;

        public SingleFlagment() {

        }


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
            Location loc = ResultStore.getResult().get(selectedPosition).getLocation();
            LatLng latLng = new LatLng(loc.getLat(), loc.getLng());

            if (mMap == null) {
                mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            }
            if (mMap != null) {
                mMap.addMarker (new MarkerOptions().position(latLng).title("INTFLOAT Co.,Ltd."));
                mMap.moveCamera
                (CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 16, 0, 0)));
            }
        }

    }
}



