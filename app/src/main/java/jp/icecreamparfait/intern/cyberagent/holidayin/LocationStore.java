package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.location.Location;

/**
 * Created by tokitake on 2014/08/23.
 */
public class LocationStore {
    private static LocationStore instance = new LocationStore();

    private static Location mLocation = null;

    public static void setLocation(Location loc) {
        mLocation = loc;
    }

    public static Location getLocation() {
        return mLocation;
    }
}
