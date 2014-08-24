package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.location.Location;

/**
 * Created by tokitake on 2014/08/23.
 */
public class LocationStore {
    private static LocationStore instance;

    private Location mLocation;

    public static LocationStore get() {
        if (instance == null) {
            instance = new LocationStore();
        }

        return instance;
    }

    public void setLocation(Location loc) {
        mLocation = loc;
    }

    public Location getLocation() {
        return mLocation;
    }
}
