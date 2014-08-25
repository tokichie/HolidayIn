package jp.icecreamparfait.intern.cyberagent.holidayin.Activities;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import jp.icecreamparfait.intern.cyberagent.holidayin.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.QueryStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;


public class SearchActivity extends Activity {

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

                Spinner spinner_required_time = (Spinner) findViewById(R.id.spinner_takingtime);
                Spinner spinner_moving_time = (Spinner) findViewById(R.id.spinner_movingtime);
                Spinner spinner_mood = (Spinner) findViewById(R.id.spinner_mood);

                QueryStore.setRequiredTime(QueryStore.fromOrdinal(
                        QueryStore.Time.class, spinner_required_time.getSelectedItemPosition()));
                QueryStore.setMovingTime(QueryStore.fromOrdinal(
                        QueryStore.Time.class, spinner_moving_time.getSelectedItemPosition()));
                QueryStore.setPlan(QueryStore.fromOrdinal(
                        QueryStore.PlanMood.class, spinner_mood.getSelectedItemPosition()));

                if (LocationStore.getLocation() == null) {
                    Toast toast = Toast.makeText(SearchActivity.this, "位置取得ができませんでした。\nGPSもしくはWifiから位置情報が取得できる場所で利用してください。", Toast.LENGTH_SHORT);
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
