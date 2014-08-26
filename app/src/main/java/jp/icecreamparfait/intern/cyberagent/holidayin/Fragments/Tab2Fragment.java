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

import java.util.ArrayList;
import java.util.List;

import jp.icecreamparfait.intern.cyberagent.holidayin.activities.PlanDetailActivity;
import jp.icecreamparfait.intern.cyberagent.holidayin.models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.PlanAdapter;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.PlanStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.R;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.ResultStore;


public class Tab2Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        if (ResultStore.getIsFound()) {
            Plan plan = ResultStore.getPlan();

            List<Plan> plans = new ArrayList<Plan>();
            plans.add(plan);
            PlanAdapter adapter = new PlanAdapter(getActivity(), 0, plans);

            PlanStore.setPlans(plans);

            ListView listView = (ListView) v.findViewById(R.id.listView_detail_plan);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
//                    String uriBegin = "geo:35.6582,139.699?q[]=";
//                    String encodedQuery1 = Uri.encode("35.6582,139.699");
//                    String encodedQuery2 = Uri.encode("35.6082,139.699");
//                    String encodedQuery3 = Uri.encode("35.7082,139.699");
//                    Uri uri = Uri.parse(uriBegin + encodedQuery1 + "&amp;q[]=" + encodedQuery2);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);

                    Intent intent = new Intent(getActivity(), PlanDetailActivity.class);
                    intent.putExtra("selectedPosition", pos);
                    startActivity(intent);
                }
            });
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

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
