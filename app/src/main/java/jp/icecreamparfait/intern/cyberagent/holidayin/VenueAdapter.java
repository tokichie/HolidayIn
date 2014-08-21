package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.condesales.models.Statistics;
import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/21.
 */
public class VenueAdapter extends ArrayAdapter<Venue> {
    private LayoutInflater layoutInflater_;

    public VenueAdapter(Context context, int textViewResourceId, List<Venue> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        Venue item = (Venue)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.detail_row, null);
        }

        TextView textView_title;
        textView_title = (TextView)convertView.findViewById(R.id.textView_title);
        textView_title.setText(item.getName());

        TextView textView_detail;
        textView_detail = (TextView) convertView.findViewById(R.id.textView_detail);
        textView_detail.setText("id: " + item.getId());


        return convertView;
    }
}
