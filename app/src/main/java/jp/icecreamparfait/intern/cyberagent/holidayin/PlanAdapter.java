package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.condesales.models.Category;
import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/21.
 */
public class PlanAdapter extends ArrayAdapter<Venue> {
    private LayoutInflater layoutInflater_;

    public PlanAdapter(Context context, int textViewResourceId, List<Venue> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        Venue venue = (Venue)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.detail_plan, null);
        }

        List<Category> categories = venue.getCategories();
        String category = "";
        for (int i = 0; i < categories.size(); i++) {
            category += categories.get(i).getName();
            if (i != categories.size()-1) category += ", ";
        }
        Log.d("icecream", category);

//        List<Tip> tips = venue.

        TextView textView_title;
        textView_title = (TextView)convertView.findViewById(R.id.textView_title);
        textView_title.setText(venue.getName());

        TextView textView_category;
        textView_category = (TextView) convertView.findViewById(R.id.textView_category);
        textView_category.setText(category);

        ImageView imageView_photo = (ImageView) convertView.findViewById(R.id.imageView_photo);

        TextView counter;
        textView_title = (TextView)convertView.findViewById(R.id.counter);
        textView_title.setText(String.valueOf(position+1));

        return convertView;
    }
}
