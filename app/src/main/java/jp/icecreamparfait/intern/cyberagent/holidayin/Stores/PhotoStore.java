package jp.icecreamparfait.intern.cyberagent.holidayin.stores;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tokitake on 2014/08/23.
 */
public class PhotoStore {
    private static PhotoStore instance;

    private static Map<String, Bitmap> mMap = new HashMap<String, Bitmap>();

//    public PhotoStore() {
//        mMap = new HashMap<String, Bitmap>();
//    }
//
//    public static PhotoStore get() {
//        if (instance == null) {
//            instance = new PhotoStore();
//        }
//
//        return instance;
//    }

    public static void putImage(String key, Bitmap bmp) {
        mMap.put(key, bmp);
    }

    public static Bitmap getImage(String key) {
        return mMap.get(key);
    }

    public static void clear() {
        mMap = null;
        mMap = new HashMap<String, Bitmap>();
    }
}
