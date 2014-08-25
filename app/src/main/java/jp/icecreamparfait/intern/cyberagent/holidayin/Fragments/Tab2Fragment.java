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

import java.util.ArrayList;
import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.criterias.VenuesCriteria;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.PlanAdapter;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.ResultStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.VenueAdapter;


public class Tab2Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        Plan plan = ResultStore.getPlan();

        List<Plan> plans = new ArrayList<Plan>();
        plans.add(plan);
        PlanAdapter adapter = new PlanAdapter(getActivity(), 0, plans);

        ListView listView = (ListView) v.findViewById(R.id.listView_detail_plan);
        listView.setAdapter(adapter);

        return v;
    }

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

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
