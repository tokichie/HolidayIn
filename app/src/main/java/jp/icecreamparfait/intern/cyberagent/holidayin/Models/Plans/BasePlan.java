package jp.icecreamparfait.intern.cyberagent.holidayin.models.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import br.com.condesales.models.Venue;
import jp.icecreamparfait.intern.cyberagent.holidayin.models.Plan;
import jp.icecreamparfait.intern.cyberagent.holidayin.stores.QueryStore;

/**
 * Created by tokitake on 2014/08/25.
 */
public class BasePlan {
    public HashMap<String, Integer> belongingCategories;
    public HashMap<String, Integer> attachments;
    public static BasePlan[] Plans = new BasePlan[] { new GracefulPlan(), new HappyPlan(), new FreePlan(), new StressFreePlan() };

    private static Plan mPlan;
    private static boolean isSetMainSpots = false;
    private static boolean isSetAttachments = false;

    public static void resetPlan() {
        mPlan = null;
        isSetMainSpots = false;
        isSetAttachments = false;
    }

    public static Plan makePlan() {
        if (isSetMainSpots && isSetAttachments) {
            return mPlan;
        }
        return null;
    }

    public static void setMainSpots(ArrayList<Venue> venues) {
        int spotsCount = QueryStore.getRequiredTime().ordinal() + 1;

        if (mPlan == null) {
            mPlan = new Plan();
        }

        while (spotsCount-- > 0) {
            Random rnd = new Random();
            int selectedPos = rnd.nextInt(venues.size());
            Venue venue = venues.get(selectedPos);
            venues.remove(selectedPos);

            mPlan.addVenue(venue);//, belongingCategories.get(venue.getCategories().get(0).getId()));
        }

        isSetMainSpots = true;
    }

    public static void setAttachments(ArrayList<Venue> venues) {
        if (mPlan == null) {
            mPlan = new Plan();
        }

        Random rnd = new Random();
        int selectedPos = rnd.nextInt(venues.size());
        Venue venue = venues.get(selectedPos);

        mPlan.setAttachment(venue);

        isSetAttachments = true;
    }
}
