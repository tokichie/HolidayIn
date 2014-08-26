package jp.icecreamparfait.intern.cyberagent.holidayin.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.activities.VenueDetailActivity;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.ResultStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.VenueAdapter;


public class Tab1Fragment extends Fragment implements AdapterView.OnItemClickListener {

    private boolean isFinished;

    private OnFragmentInteractionListener mListener;

    public Tab1Fragment() {
        isFinished = false;
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

        View v = inflater.inflate(R.layout.fragment_tab1_loading, container, false);
        if (isFinished && ResultStore.getIsFound()) {
            v = inflater.inflate(R.layout.fragment_tab1, container, false);
            List<Venue> venues = ResultStore.getResult();

            VenueAdapter adapter = new VenueAdapter(getActivity(), 0, venues);

            ListView listView = (ListView) v.findViewById(R.id.listView_detail_spot);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent intent = new Intent(getActivity(), VenueDetailActivity.class);
        intent.putExtra("selectedPosition", pos);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_right, R.anim.out_left);
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
