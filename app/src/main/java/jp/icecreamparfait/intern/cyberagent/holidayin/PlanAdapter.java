package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.listeners.VenuePhotosListener;
import br.com.condesales.models.Category;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plan;

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

        TextView textView_counter;
        textView_counter = (TextView)convertView.findViewById(R.id.textView_counter);
        textView_counter.setText("プラン" + String.valueOf(position+1));

        for (Venue venue: plan.getMainVenues()) {
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.linearLayout_planItems);

            View v = layoutInflater_.inflate(R.layout.detail_row, null);

            VenueDetailSetter.set(mActivity, venue, v);

            layout.addView(v);
        }

        return convertView;

    }
}
