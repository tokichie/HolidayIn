package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.Venue;


public class DetailActivity extends Activity {

    static List<Venue> dataList = new ArrayList<Venue>();
    static VenueAdapter adapter;

    private List<Venue> venueList;

    public void setVenueList(ArrayList<Venue> venues) {
        venueList = venues;
    }

    private void setVenues(ArrayList<Venue> venues) {
        //List<Venue> venues = (List<Venue>) getIntent().getSerializableExtra("venue");

        VenueAdapter adapter = new VenueAdapter(this, 0, venues);

        ListView listView = (ListView) findViewById(R.id.listView_detail);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        EasyFoursquare efs = new EasyFoursquare(DetailActivity.this);
        VenuesCriteria criteria = new VenuesCriteria();
        Location loc = new Location("");
        loc.setLongitude(139.7069874);
        loc.setLatitude(35.6432274);

        criteria.setQuantity(10);
        criteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        criteria.setQuery(getIntent().getExtras().getString("query"));
        criteria.setLocation(loc);

        ArrayList<Venue> venues = efs.getVenuesNearby(criteria);
        Log.d("Icecream", venues.toString());

        setVenues(venues);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

}
