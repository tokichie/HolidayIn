package jp.icecreamparfait.intern.cyberagent.holidayin.stores;

/**
 * Created by tokitake on 2014/08/23.
 */
public class QueryStore {
    private static QueryStore instance = new QueryStore();

    private static String mQuery = "";

    private static Time mRequiredTime = Time.Short;

    private static Time mMovingTime = Time.Short;

    private static PlanMood mPlanMood = PlanMood.Graceful;

    public enum Time{
        Short,
        Middle,
        Long;
    };

    public enum PlanMood{
        Graceful,
        Happy,
        Free,
        StressFree;
    }

    public static <E extends Enum<E>> E fromOrdinal(Class<E> enumClass, int ordinal) {
        E[] enumArray = enumClass.getEnumConstants();
        return enumArray[ordinal];
    }

    public static void setQuery(String query) {
        mQuery = query;
    }

    public static void setRequiredTime(Time requiredTime) { mRequiredTime = requiredTime; }

    public static void setMovingTime(Time movingTime) { mMovingTime = movingTime; }

    public static void setPlan(PlanMood planMood) { mPlanMood = planMood; }

    public static String getQuery() { return mQuery; }

    public static Time getRequiredTime() { return mRequiredTime; }

    public static Time getMovingTime() { return mMovingTime; }

    public static PlanMood getPlanMood() { return mPlanMood; }
}
