package jp.icecreamparfait.intern.cyberagent.holidayin;

import java.util.List;

import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/23.
 */
public class QueryStore {
    private static QueryStore instance = new QueryStore();

    private static String mQuery = "ebisu";

    private static RequiredTime mRequiredTime = RequiredTime.Light;

    private static MovingTime mMovingTime = MovingTime.Short;

    public enum RequiredTime{
        Light,
        Middle,
        Heavy;
    };

    public enum MovingTime{
        Short,
        Middle,
        Long;
    }

    public static <E extends Enum<E>> E fromOrdinal(Class<E> enumClass, int ordinal) {
        E[] enumArray = enumClass.getEnumConstants();
        return enumArray[ordinal];
    }

    public static void setQuery(String query) {
        mQuery = query;
    }

    public static void setRequiredTime(RequiredTime requiredTime) { mRequiredTime = requiredTime; }

    public static void setMovingTime(MovingTime movingTime) { mMovingTime = movingTime; }

    public static String getQuery() { return mQuery; }

    public static RequiredTime getRequiredTime() { return mRequiredTime; }

    public static MovingTime getMovingTime() { return mMovingTime; }
}
