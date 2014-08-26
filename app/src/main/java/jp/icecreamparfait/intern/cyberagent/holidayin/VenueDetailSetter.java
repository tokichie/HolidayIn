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
import br.com.condesales.models.Location;
import br.com.condesales.models.PhotoItem;
import br.com.condesales.models.PhotosGroup;
import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.LocationStore;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.PhotoStore;

/**
 * Created by tokitake on 2014/08/25.
 */
public class VenueDetailSetter {
    private static final double WGS84_A = 6378137.000;
    private static final double WGS84_E2 = 0.00669437999019758;
    private static final double WGS84_MNUM = 6335439.32729246;

    public static double deg2rad(double deg){
        return deg * Math.PI / 180.0;
    }

    public static double calculateDistance(android.location.Location loc1, Location loc2){
        double lat1 = loc1.getLatitude();
        double lat2 = loc2.getLat();
        double lng1 = loc1.getLongitude();
        double lng2 = loc2.getLng();
        double a = WGS84_A;
        double e2 = WGS84_E2;
        double mnum = WGS84_MNUM;

        double my = deg2rad((lat1 + lat2) / 2.0);
        double dy = deg2rad(lat1 - lat2);
        double dx = deg2rad(lng1 - lng2);

        double sin = Math.sin(my);
        double w = Math.sqrt(1.0 - e2 * sin * sin);
        double m = mnum / (w * w * w);
        double n = a / w;

        double dym = dy * m;
        double dxncos = dx * n * Math.cos(my);

        double res = Math.sqrt(dym * dym + dxncos * dxncos);

        return res / 1000;
    }

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

        TextView textView_distance = (TextView) view.findViewById(R.id.textView_distance);
        double distance = calculateDistance(LocationStore.getLocation(), venue.getLocation());
        textView_distance.setText(String.format("%.2f", distance) + " km");


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
