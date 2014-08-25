package jp.icecreamparfait.intern.cyberagent.holidayin.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import jp.icecreamparfait.intern.cyberagent.holidayin.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.TokenStore;

/**
 * Created by guest on 2014/08/24.
 */
public class TutorialActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        Button button_searchtutorial = (Button) findViewById(R.id.button_searchtutorial);
        button_searchtutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri uri = Uri.parse("https://foursquare.com/venue/503de4dce4b0857b003af5f7");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                Intent intent_search = new Intent(TutorialActivity.this, SearchActivity.class);
                startActivity(intent_search);
            }
        });



}}
