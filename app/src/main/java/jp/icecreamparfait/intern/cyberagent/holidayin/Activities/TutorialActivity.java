package jp.icecreamparfait.intern.cyberagent.holidayin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jp.icecreamparfait.intern.cyberagent.holidayin.R;

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
