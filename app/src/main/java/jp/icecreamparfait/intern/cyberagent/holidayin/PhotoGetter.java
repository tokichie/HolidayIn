package jp.icecreamparfait.intern.cyberagent.holidayin;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tokitake on 2014/08/22.
 */
public class PhotoGetter extends AsyncTask<String, Void, Bitmap>{

    private ImageView mImageView;
    private int mWidth;
    private int mHeight;

    public PhotoGetter(ImageView imageView) {
        mImageView = imageView;
        mWidth = imageView.getWidth();
        mHeight = imageView.getHeight();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        //Bitmap bmp;// = Bitmap.createBitmap(mWidth, mHeight, null);
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

        //return null;
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        mImageView.setImageBitmap(bmp);
    }
}
