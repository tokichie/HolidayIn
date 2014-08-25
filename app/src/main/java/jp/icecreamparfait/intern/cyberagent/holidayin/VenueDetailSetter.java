package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.condesales.EasyFoursquareAsync;
import br.com.condesales.listeners.VenuePhotosListener;
import br.com.condesales.models.Category;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/25.
 */
public class VenueDetailSetter {
    public static void set(Activity activity, Venue venue, View view) {
        List<Category> categories = venue.getCategories();
        String category = "";
        for (int i = 0; i < categories.size(); i++) {
            category += categories.get(i).getName();
            if (i != categories.size()-1) category += ", ";
        }

        TextView textView_title = (TextView)view.findViewById(R.id.textView_title);
        textView_title.setText(venue.getName());

        TextView textView_category = (TextView) view.findViewById(R.id.textView_category);
        textView_category.setText(category);

        TextView textView_location = (TextView) view.findViewById(R.id.textView_location);
        textView_location.setText(venue.getLocation().getCity());

        final ImageView imageView_photo = (ImageView) view.findViewById(R.id.imageView_photo);
        imageView_photo.setTag(venue.getId());
        imageView_photo.setImageResource(R.drawable.noimage);

        Bitmap img = PhotoStore.getImage(venue.getId());
        if (img != null) {
            imageView_photo.setImageBitmap(img);
        } else {
            EasyFoursquareAsync api = new EasyFoursquareAsync(activity);
            api.getVenuePhotos(venue.getId(), new VenuePhotosListener() {
                @Override
                public void onGotVenuePhotos(PhotosGroup photosGroup) {
                    if (photosGroup.getCount() > 0) {
                        List<PhotoItem> photoItems = photosGroup.getItems();
                        String prefix = photoItems.get(0).getPrefix();
                        String suffix = photoItems.get(0).getSuffix();

                        PhotoGetter getter = new PhotoGetter(imageView_photo);
                        getter.execute(prefix + "200x200" + suffix);
                    }
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
    }
}
