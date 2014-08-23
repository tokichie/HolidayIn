package jp.icecreamparfait.intern.cyberagent.holidayin;

import java.util.List;

import br.com.condesales.models.Venue;

/**
 * Created by tokitake on 2014/08/23.
 */
public class ResultStore {
    private static ResultStore instance;

    private String mQuery = "ebisu";

    private List<Venue> mVenues;

    private boolean isStored = false;

    public static ResultStore get() {
        if (instance == null) {
            instance = new ResultStore();
        }

        return instance;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    public void setResult(List<Venue> venues) {
        mVenues = venues;
        isStored = true;
    }

    public String getQuery() { return mQuery; }

    public List<Venue> getResult() { return mVenues; }

    public boolean getIsStored() { return isStored; }

    public void reset() {
        mQuery = null;
        mVenues = null;
        isStored = false;
    }
}
