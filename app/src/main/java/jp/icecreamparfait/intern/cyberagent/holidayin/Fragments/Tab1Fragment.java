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
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.CheckInCriteria;
import br.com.condesales.criterias.TipsCriteria;
import br.com.condesales.criterias.TrendingVenuesCriteria;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.Checkin;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Score;
import br.com.condesales.models.ScoreItem;
import br.com.condesales.models.Statistics;
import br.com.condesales.models.Tip;
import br.com.condesales.models.TipItem;
import br.com.condesales.models.TipsGroup;
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
import jp.icecreamparfait.intern.cyberagent.holidayin.PhotoGetter;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;
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

    private boolean isFinished;


    private OnFragmentInteractionListener mListener;

    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isFinished = getArguments().getBoolean("finished");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("icecream", String.valueOf(container.getId()));
        View v = inflater.inflate(R.layout.fragment_tab1_loading, container, false);
        if (isFinished) {
            Log.d("icecream", "finished");
            v = inflater.inflate(R.layout.fragment_tab1, container, false);
            List<Venue> venues = ResultStore.get().getResult();


            VenueAdapter adapter = new VenueAdapter(getActivity(), 0, venues);

            ListView listView = (ListView) v.findViewById(R.id.listView_detail);
            listView.setAdapter(adapter);
        }
//        List<Venue> venues = search(query, v);

        return v;
    }

//    private List<Venue> search(String query, View v) {
//        EasyFoursquare efs = new EasyFoursquare(getActivity());
//
//        Location loc = new Location("");
//        loc.setLongitude(139.7069874);
//        loc.setLatitude(35.6432274);
//
//        VenuesCriteria vCriteria = new VenuesCriteria();
//        vCriteria.setQuantity(10);
//        vCriteria.setIntent(VenuesCriteria.VenuesCriteriaIntent.CHECKIN);
//        vCriteria.setQuery(query);
//        vCriteria.setLocation(loc);
//
//        List<Venue> venues = efs.getVenuesNearby(vCriteria);
//
//        TipsGroup tipsGroup = efs.getVenueTips(venues.get(0).getId());
//        List<TipItem> tipItems = tipsGroup.getItems();
//        TipItem tipItem = tipItems.get(0);
//
//
//
//
//        return venues;
//    }

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
