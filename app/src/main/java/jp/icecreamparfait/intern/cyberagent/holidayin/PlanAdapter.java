package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.ResultStore;

/**
 * Created by tokitake on 2014/08/21.
 */
public class PlanAdapter extends ArrayAdapter<Plan> {
    private LayoutInflater layoutInflater_;
    private Activity mActivity;

    public PlanAdapter(Activity activity, int textViewResourceId, List<Plan> plans) {
        super(activity, textViewResourceId, plans);
        layoutInflater_ = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        if (ResultStore.getIsFound()) {
            TextView textView_counter;
            textView_counter = (TextView) convertView.findViewById(R.id.textView_counter);
            textView_counter.setText("プラン" + String.valueOf(position + 1));

            List<Venue> venues = plan.getMainVenues();
            if (!venues.get(venues.size() - 1).equals(plan.getAttachment())) {
                venues.add(plan.getAttachment());
            }

            for (Venue venue : venues) {
                LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.linearLayout_planItems);

                View v = layoutInflater_.inflate(R.layout.detail_row, null);

                VenueDetailSetter.set(mActivity, venue, v);

                LinearLayout layout_shadow = (LinearLayout) v.findViewById(R.id.linearLayout_shadow);
                ((ViewGroup) v).removeView(layout_shadow);

                layout.addView(v);
            }
        }

        return convertView;

    }
}
