package jp.icecreamparfait.intern.cyberagent.holidayin.stores;

import java.util.List;

import jp.icecreamparfait.intern.cyberagent.holidayin.models.Plan;

/**
 * Created by tokitake on 2014/08/23.
 */
public class PlanStore {
    private static PlanStore instance = new PlanStore();

    private static List<Plan> mPlans;

    public static void setPlans(List<Plan> plans) {
        mPlans = plans;
    }

    public static List<Plan> getPlans() { return mPlans; }
}
