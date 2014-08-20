package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.foursquare.android.nativeoauth.FoursquareOAuth;

import java.util.ArrayList;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.Venue;


public class Search extends Activity {

    private static final int REQUEST_CODE_FSQ_CONNECT = 200;
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201;
    private static final String DEFAULT_VERSION_DATE = "20140820";
    private static final String CLIENT_ID = "UJ4ENP0LIKT3EQNUVVLQXFTKNUTWZZSF5BB0YFHVX3NA1WNM";
    private static final String CLIENT_SECRET = "I3OIFOA0KGRLH5UTR2WSKXEMW2JLYG20NOVXX0PATFELX3P4";
    private static final String REDIRECT_URL = "https://github.com/techcampman/tokyo_smartphone1_C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.add("1h");
        adapter.add("2h");
        adapter.add("3h");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        Button button_oauth = (Button) findViewById(R.id.button_oauth);
        button_oauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = FoursquareOAuth.getConnectIntent(Search.this, CLIENT_ID);
                if (FoursquareOAuth.isPlayStoreIntent(intent)) {
                    //toastMessage(Search.this, "アプリがインストールされていません。");
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT);
                }
            }
        });

        Button button_search = (Button) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyFoursquare efs = new EasyFoursquare(Search.this);
                VenuesCriteria criteria = new VenuesCriteria();
                Location loc = new Location("");
                loc.setLatitude(41.7);
                loc.setAltitude(70.0);
                criteria.setLocation(loc);
                ArrayList<Venue> venues = efs.getVenuesNearby(criteria);
                Log.d("Icecream", venues.toString());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
