package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by tokitake on 2014/08/22.
 */
public class ApiHelper extends AsyncTaskLoader {

    public ApiHelper(Context context) {
        super(context);

    }

    @Override
    public Object loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
