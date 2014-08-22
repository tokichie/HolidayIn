package jp.icecreamparfait.intern.cyberagent.holidayin.Fragments;


import android.app.Activity;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.CheckInCriteria;
import br.com.condesales.criterias.TrendingVenuesCriteria;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.Checkin;
import br.com.condesales.models.Score;
import br.com.condesales.models.ScoreItem;
import br.com.condesales.models.Statistics;
import br.com.condesales.models.Venue;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.CompleteSpecial;
import fi.foyt.foursquare.api.entities.CompleteTip;
import fi.foyt.foursquare.api.entities.Stats;
import fi.foyt.foursquare.api.entities.TipGroup;
import fi.foyt.foursquare.api.entities.Tips;
import fi.foyt.foursquare.api.entities.VenueGroup;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import jp.icecreamparfait.intern.cyberagent.holidayin.Activities.MainActivity;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.VenueAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Tab1Fragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Venue> mVenues;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */

    public static Tab1Fragment newInstance(String param1, String param2) {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);

        Bundle bundle = getArguments();
        String query = bundle.getString("param1");
        Log.d("icecream", query);

        List<Venue> venues = search(query);
        //search2();

        Log.d("parfait", venues.toString());

        VenueAdapter adapter = new VenueAdapter(getActivity(), 0, venues);

        ListView listView = (ListView) v.findViewById(R.id.listView_detail);
        listView.setAdapter(adapter);

        return v;
    }

    private List<Venue> search(String query) {
        EasyFoursquare efs = new EasyFoursquare(getActivity());

        Location loc = new Location("");
        loc.setLongitude(139.7069874);
        loc.setLatitude(35.6432274);

        VenuesCriteria vCriteria = new VenuesCriteria();
        vCriteria.setQuantity(10);
        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
        vCriteria.setQuery(query);
        vCriteria.setLocation(loc);

        List<Venue> venues = efs.getVenuesNearby(vCriteria);


        /*

        CheckInCriteria ciCriteria = new CheckInCriteria();
        ciCriteria.setVenueId(venues.get(0).getId());
        ciCriteria.setLocation(loc);


        Checkin checkin = efs.checkIn(ciCriteria);

        Log.d("icecream", checkin.toString());

        Score score = checkin.getScore();
        List<ScoreItem> scoreItems = score.getScores();
        Log.d("icecream", scoreItems.get(0).toString());
        */

        return venues;
    }

    private void search2() {
        FoursquareApi api = new FoursquareApi(MainActivity.CLIENT_ID, MainActivity.CLIENT_SECRET, MainActivity.REDIRECT_URL);
        Result<VenuesSearchResult> result = null;
        try {
            result = api.venuesSearch("139.7069,35.6432", null, null, null, "ebisu", null, null, null, null, null, null);
        } catch (FoursquareApiException e) {
            e.printStackTrace();
        }

        CompactVenue[] venues = result.getResult().getVenues();
        CompleteSpecial[] specials = venues[0].getSpecials();

        Log.d("icecream", venues.toString());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
