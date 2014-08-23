package br.com.condesales.tasks.venues;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import br.com.condesales.constants.FoursquareConstants;
import br.com.condesales.listeners.VenuePhotosListener;
import br.com.condesales.listeners.VenueTipsListener;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.TipItem;
import br.com.condesales.models.TipsGroup;

/**
 * Created by dionysis_lorentzos on 2/8/14.
 * All rights reserved by the Author.
 * Use with your own responsibility.
 */

public class GetVenueTipsRequest extends AsyncTask<String, Integer, TipsGroup> {

    private VenueTipsListener mListener;
    private String mVenueID;

    public GetVenueTipsRequest(Activity activity, VenueTipsListener listener, String venueID) {
        mListener = listener;
        mVenueID = venueID;
    }


    @Override
    protected TipsGroup doInBackground(String... params) {

        String access_token = params[0];
        ArrayList<TipItem> tips = new ArrayList<TipItem>();
        TipsGroup tipsGroup = new TipsGroup();

        try {

            //date required
            String apiDateVersion = FoursquareConstants.API_DATE_VERSION;
            // Call Foursquare to get the Venues around
            String uri = "https://api.foursquare.com/v2/venues/" + mVenueID
                    + "/tips?v="
                    + apiDateVersion;
            if (!access_token.equals("")) {
                uri = uri + "&oauth_token=" + access_token;
            } else {
                uri = uri + "&client_id=" + FoursquareConstants.CLIENT_ID + "&client_secret=" + FoursquareConstants.CLIENT_SECRET;
            }

            JSONObject tipsJson = executeHttpGet(uri);

            // Get return code
            int returnCode = Integer.parseInt(tipsJson.getJSONObject("meta")
                    .getString("code"));
            // 200 = OK
            if (returnCode == 200) {
                Gson gson = new Gson();
                JSONObject json = tipsJson.getJSONObject("response").getJSONObject("tips");
                tipsGroup = gson.fromJson(json.toString(), TipsGroup.class);
            } else {
                if (mListener != null)
                    mListener.onError(tipsJson.getJSONObject("meta").getString("errorDetail"));
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            if (mListener != null)
                mListener.onError(exp.toString());
        }

        return tipsGroup;
    }


    @Override
    protected void onPostExecute(TipsGroup tipsGroup) {
        if (mListener != null)
            mListener.onGotVenueTips(tipsGroup);
        super.onPostExecute(tipsGroup);
    }


    // Calls a URI and returns the answer as a JSON object
    private JSONObject executeHttpGet(String uri) throws Exception {
        HttpGet req = new HttpGet(uri);

        HttpClient client = new DefaultHttpClient();
        HttpResponse resLogin = client.execute(req);
        BufferedReader r = new BufferedReader(new InputStreamReader(resLogin
                .getEntity().getContent()));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = r.readLine()) != null) {
            sb.append(s);
        }

        return new JSONObject(sb.toString());
    }

}
