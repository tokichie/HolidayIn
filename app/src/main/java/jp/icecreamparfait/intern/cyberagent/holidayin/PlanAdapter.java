package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.condesales.models.Category;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plan;

/**
 * Created by tokitake on 2014/08/21.
 */
public class PlanAdapter extends ArrayAdapter<Plan> {
    private LayoutInflater layoutInflater_;
    private Activity mActivity;

    public PlanAdapter(Context context, int textViewResourceId, List<Plan> plans, Activity activity) {
        super(context, textViewResourceId, plans);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        Plan plan = getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.detail_plan, null);
        }

        TextView textView_counter;
        textView_counter = (TextView)convertView.findViewById(R.id.textView_counter);
        textView_counter.setText("プラン" + String.valueOf(position+1));

        ListView listView_planItems = (ListView) convertView.findViewById(R.id.listView_planItems);
        Log.d("icecream", plan.getMainVenues().toString());
        VenueAdapter adapter = new VenueAdapter(mActivity, 0, plan.getMainVenues());
        listView_planItems.setAdapter(adapter);

        Log.d("icecream", String.valueOf(listView_planItems.getLayoutParams().height));

        ListView listView_planDetail = (ListView) parent.findViewById(R.id.listView_detail_plan);
//        listView_planDetail
        return convertView;

    }
}
