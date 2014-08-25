package jp.icecreamparfait.intern.cyberagent.holidayin.Models;

import java.util.ArrayList;
import java.util.List;

import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/25.
 */
public class Plan {

    private List<Venue> mMainVenues;
    private Venue mAttachment;
    private List<Integer> mRequiredTime;

    public Plan() {
        mMainVenues = new ArrayList<Venue>();
        mRequiredTime = new ArrayList<Integer>();
    }

    public void addVenue(Venue venue) {
        mMainVenues.add(venue);
    }

    public void addVenue(Venue venue, int requiredTime) {
        mMainVenues.add(venue);
        mRequiredTime.add(requiredTime);
    }

    public void setAttachment(Venue venue) {
        mAttachment = venue;
    }

    public List<Venue> getMainVenues() { return mMainVenues; }
    public Venue getAttachment() { return mAttachment; }
    public List<Integer> getRequiredTime() { return mRequiredTime; }
}
