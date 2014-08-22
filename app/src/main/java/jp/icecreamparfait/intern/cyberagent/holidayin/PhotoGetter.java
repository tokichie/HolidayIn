package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by tokitake on 2014/08/22.
 */
public class PhotoGetter extends AsyncTaskLoader{

    public PhotoGetter(Context context) {
        super(context);

    }


    @Override
    public Object loadInBackground() {
        return null;
    }
}
