package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.location.Location;

import java.util.HashMap;
import java.util.Map;

import br.com.condesales.models.PhotosGroup;

/**
 * Created by tokitake on 2014/08/23.
 */
public class PhotoStore {
    private static PhotoStore instance;

    private Map<String, PhotosGroup> mMap;

    public PhotoStore() {
        mMap = new HashMap<String, PhotosGroup>();
    }

    public static PhotoStore get() {
        if (instance == null) {
            instance = new PhotoStore();
        }

        return instance;
    }

    public void putPhotosGroup(String key, PhotosGroup photosGroup) {
        mMap.put(key, photosGroup);
    }

    public PhotosGroup getPhotosGroup(String key) {
        return mMap.get(key);
    }
}
