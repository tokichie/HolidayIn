package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;


public class Main extends Activity {

    private static final int REQUEST_CODE_FSQ_CONNECT = 200;
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201;
    private static final String DEFAULT_VERSION_DATE = "20140820";
    private static final String CLIENT_ID = "UJ4ENP0LIKT3EQNUVVLQXFTKNUTWZZSF5BB0YFHVX3NA1WNM";
    private static final String CLIENT_SECRET = "I3OIFOA0KGRLH5UTR2WSKXEMW2JLYG20NOVXX0PATFELX3P4";
    private static final String REDIRECT_URL = "https://github.com/techcampman/tokyo_smartphone1_C";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_no_login = (Button) findViewById(R.id.button_nologin);
        Button button_login = (Button) findViewById(R.id.button_login);

        button_no_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearchActivity();
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent_oauth = FoursquareOAuth.getConnectIntent(Main.this, CLIENT_ID);

                if (FoursquareOAuth.isPlayStoreIntent(intent_oauth)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                    builder.setMessage("Foursquareのアプリをインストールする必要があります。アプリのページに移動しますか？");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(intent_oauth);
                        }
                    });
                    builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                } else {
                    startActivityForResult(intent_oauth, REQUEST_CODE_FSQ_CONNECT);
                }
            }
        });
    }

    private void startSearchActivity() {
        Intent intent_search = new Intent(Main.this, Search.class);
        startActivity(intent_search);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FSQ_CONNECT:
                AuthCodeResponse codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data);
                Log.d("icecream", codeResponse.getCode().toString());
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
