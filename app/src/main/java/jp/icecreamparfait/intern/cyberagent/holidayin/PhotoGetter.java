package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * Created by tokitake on 2014/08/22.
 */
public class PhotoGetter extends AsyncTask<String, Void, Bitmap>{

    private ImageView mImageView;
    private Object mTag;

    public PhotoGetter(ImageView imageView) {
        mImageView = imageView;
        mTag = imageView.getTag();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream is = connection.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);

            return bmp;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        if (mTag != null && mTag.equals(mImageView.getTag())) {
            PhotoStore.putImage(mTag.toString(), bmp);
            if (bmp != null) {
                mImageView.setImageBitmap(bmp);
            }
        }
    }
}
