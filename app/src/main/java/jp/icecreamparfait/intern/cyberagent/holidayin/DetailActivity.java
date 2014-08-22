package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;


public class DetailActivity extends Activity implements Tab1Fragment.OnFragmentInteractionListener {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_detail);

                // Set up the action bar.
                final ActionBar actionBar = getActionBar();

                Log.d("aaaa","" + actionBar);

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
            public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.detail, menu);
                return true;
            }


       // </tab2Fragment></Tab1Fragment>



  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }*/




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
