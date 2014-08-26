package jp.icecreamparfait.intern.cyberagent.holidayin;

import java.util.List;

import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.Models.Plan;

/**
 * Created by tokitake on 2014/08/23.
 */
public class ResultStore {
    private static ResultStore instance = new ResultStore();

    private static List<Venue> mVenues;

    private static Plan mPlan;

    private static boolean mIsFound = true;

    public static void setIsFound(boolean isFound) { mIsFound = isFound; }

    public static void setPlan(Plan plan) { mPlan = plan; }

    public static void setResult(List<Venue> venues) { mVenues = venues; }

    public static boolean getIsFound() { return mIsFound; }

    public static Plan getPlan() { return mPlan; }

    public static List<Venue> getResult() { return mVenues; }

    public static void reset() {
        mVenues = null;
        mPlan = null;
        mIsFound = true;
    }
}
