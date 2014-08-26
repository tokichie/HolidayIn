package jp.icecreamparfait.intern.cyberagent.holidayin.Activities;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import jp.icecreamparfait.intern.cyberagent.holidayin.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.QueryStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.RadioButtonIdHash;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;


public class SearchActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button button_search = (Button) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_keyword = (EditText) findViewById(R.id.editText_keyword);

                String query = editText_keyword.getText().toString();
                QueryStore.setQuery(query);

                RadioGroup rg_required_time = (RadioGroup) findViewById(R.id.radioGroup_requiredTime);
                RadioGroup rg_moving_time = (RadioGroup) findViewById(R.id.radioGroup_movingTime);
                RadioGroup rg_mood = (RadioGroup) findViewById(R.id.radioGroup_mood);

                QueryStore.setRequiredTime(QueryStore.fromOrdinal(
                        QueryStore.Time.class, RadioButtonIdHash.hashForRequiredTime.get(rg_required_time.getCheckedRadioButtonId())));
                QueryStore.setMovingTime(QueryStore.fromOrdinal(
                        QueryStore.Time.class, RadioButtonIdHash.hashForMovingTime.get(rg_moving_time.getCheckedRadioButtonId())));
                QueryStore.setPlan(QueryStore.fromOrdinal(
                        QueryStore.PlanMood.class, RadioButtonIdHash.hashForMood.get(rg_mood.getCheckedRadioButtonId())));

                if (LocationStore.getLocation() == null) {
                    Toast toast = Toast.makeText(SearchActivity.this, "位置情報が取得できませんでした。\nGPSもしくはWifiから位置情報が取得できる場所で利用してください。", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                startActivity(intent);
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
