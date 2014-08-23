package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.condesales.EasyFoursquare;
import br.com.condesales.models.Category;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Tip;
import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/21.
 */
public class VenueAdapter extends ArrayAdapter<Venue> {
    private LayoutInflater layoutInflater_;
    private Activity mActivity;

    public VenueAdapter(Activity activity, int textViewResourceId, List<Venue> objects) {
        super(activity, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        Venue venue = (Venue)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.detail_row, null);
        }

        List<Category> categories = venue.getCategories();
        String category = "";
        for (int i = 0; i < categories.size(); i++) {
            category += categories.get(i).getName();
            if (i != categories.size()-1) category += ", ";
        }

        TextView textView_title;
        textView_title = (TextView)convertView.findViewById(R.id.textView_title);
        textView_title.setText(venue.getName());

        TextView textView_category;
        textView_category = (TextView) convertView.findViewById(R.id.textView_category);
        textView_category.setText(category);

        ImageView imageView_photo = (ImageView) convertView.findViewById(R.id.imageView_photo);

        EasyFoursquare api = new EasyFoursquare(mActivity);
        PhotosGroup photosGroup = api.getVenuePhotos(venue.getId());
        if (photosGroup.getCount() > 0) {
            List<PhotoItem> photoItems = photosGroup.getItems();
            String prefix = photoItems.get(0).getPrefix();
            String suffix = photoItems.get(0).getSuffix();

            PhotoGetter getter = new PhotoGetter(imageView_photo);
            getter.execute(prefix + "200x200" + suffix);
        }


        return convertView;
    }
}
